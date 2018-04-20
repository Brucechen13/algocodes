package algo.depthfirst;

import com.sun.org.apache.bcel.internal.generic.DADD;
import com.sun.org.apache.bcel.internal.generic.FADD;
import com.sun.xml.internal.fastinfoset.util.StringArray;

import java.util.*;

public class AccountsMerge {
    class PersonNode{
        public String userName;
        public Set<MailNode> mails = new HashSet<>();
        public boolean isValue;

        public void cover(PersonNode node){
            this.userName = node.userName;
            this.mails.addAll(node.mails);
            this.isValue = true;
        }

        public void addMore(PersonNode node){
            this.mails.addAll(node.mails);
        }
    }

    class MailNode{
        public PersonNode user;
        public String mailUrl;
        public MailNode(PersonNode user, String mailUrl){
            this.user = user;
            this.mailUrl = mailUrl;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MailNode mailNode = (MailNode) o;

            return mailUrl != null ? mailUrl.equals(mailNode.mailUrl) : mailNode.mailUrl == null;
        }

        @Override
        public int hashCode() {
            return mailUrl != null ? mailUrl.hashCode() : 0;
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        Map<String, MailNode> cached = new HashMap<>();

        for(List<String> accs : accounts){
            Set<MailNode> nodes = new HashSet<>();
            PersonNode user = new PersonNode();
            for(int i = 1; i < accs.size(); i ++){
                String acc = accs.get(i);
                MailNode node = new MailNode(user, acc);
                if(nodes.contains(node))continue;
                if(cached.containsKey(acc)){
                    if(!user.isValue){
                        user.cover(cached.get(acc).user);
                    }else{
                        user.addMore(cached.get(acc).user);
                    }
                    Set<MailNode> mailNodes = cached.get(acc).user.mails;
                    for(MailNode preNode : mailNodes){
                        preNode.user = user;
                    }
                }else{
                    nodes.add(node);
                    cached.put(acc, node);
                }
            }
            if(!user.isValue){
                user.userName = accs.get(0);
                user.isValue = true;
                user.mails = new HashSet<>();
            }
            user.mails.addAll(nodes);
        }
        Set<PersonNode> allUsers = new HashSet<>();
        for(String mailUrl : cached.keySet()){
            allUsers.add(cached.get(mailUrl).user);
        }
        List<List<String>> res = new ArrayList<>();
        for(PersonNode node : allUsers){
            List<String> mails = new ArrayList<>();
            mails.add(node.userName);
            for (MailNode mailNode:
                    node.mails) {
                mails.add(mailNode.mailUrl);
            }
            Collections.sort(mails);
            res.add(mails);
        }
        return res;
    }


    public List<List<String>> accountsMerge2(List<List<String>> acts) {
        Map<String, String> owner = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, TreeSet<String>> unions = new HashMap<>();
        for (List<String> a : acts) {
            for (int i = 1; i < a.size(); i++) {
                parents.put(a.get(i), a.get(i));
                owner.put(a.get(i), a.get(0));
            }
        }
        for (List<String> a : acts) {
            String p = find(a.get(1), parents);
            for (int i = 2; i < a.size(); i++)
                parents.put(find(a.get(i), parents), p);
        }
        for(List<String> a : acts) {
            String p = find(a.get(1), parents);
            if (!unions.containsKey(p)) unions.put(p, new TreeSet<>());
            for (int i = 1; i < a.size(); i++)
                unions.get(p).add(a.get(i));
        }
        List<List<String>> res = new ArrayList<>();
        for (String p : unions.keySet()) {
            List<String> emails = new ArrayList(unions.get(p));
            emails.add(0, owner.get(p));
            res.add(emails);
        }
        return res;
    }
    private String find(String s, Map<String, String> p) {
        return p.get(s) == s ? s : find(p.get(s), p);
    }

    public static void main(String[] args){
        List<List<String>> data = new ArrayList<>();
        String[] d1 = {"David","David0@m.co","David4@m.co","David3@m.co"};
        data.add(Arrays.asList(d1));
        String[] d2 = {"David","David5@m.co","David5@m.co","David0@m.co"};
        data.add(Arrays.asList(d2));
        String[] d3 = {"David","David1@m.co","David4@m.co","David0@m.co"};
        data.add(Arrays.asList(d3));
        String[] d4 = {"David","David0@m.co","David1@m.co","David3@m.co"};
        data.add(Arrays.asList(d4));
        String[] d5 = {"David","David4@m.co","David1@m.co","David3@m.co"};
        data.add(Arrays.asList(d5));

        new AccountsMerge().accountsMerge(data);
    }
}
