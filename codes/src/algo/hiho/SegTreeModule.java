package algo.hiho;

public class SegTreeModule {
    private static int[] ans;//区间结果
    private static int[] lazy;//延迟更新缓存结果
    private static int[] a;//单点结果

    static void build(int l, int r, int rt){
        if (l==r)
        {
            ans[rt]=a[l];
            return;
        }
        int mid=(l+r)>>1;
        build(l,mid,rt<<1);
        build(mid+1,r,rt<<1|1);
        pushUp(rt);
    }

    static void pushUp(int rt){
        //根据左右子区间更新当前区间
        //ans[rt]=ans[rt<<1]+ans[rt<<1|1];
    }

    static void pushDown(int rt,int ln,int rn){
        if (lazy[rt] != 0)
        {
            lazy[rt<<1]+=lazy[rt];
            lazy[rt<<1|1]+=lazy[rt];
            ans[rt<<1]+=lazy[rt]*ln;
            ans[rt<<1|1]+=lazy[rt]*rn;
            lazy[rt]=0;
        }
    }

    static void add(int L,int C,int l,int r,int rt)
    {
        if (l==r)
        {
            ans[rt]+=C;
            return;
        }
        int mid=(l+r)>>1;
        //PushDown(rt,mid-l+1,r-mid); 若既有点更新又有区间更新，需要这句话
        if (L<=mid)
            add(L,C,l,mid,rt<<1);
        else
            add(L,C,mid+1,r,rt<<1|1);
        pushUp(rt);
    }

    static void update(int L,int R,int C,int l,int r,int rt)
    {
        if (L<=l&&r<=R)
        {
            ans[rt]+=C*(r-l+1);
            lazy[rt]+=C;
            return;
        }
        int mid=(l+r)>>1;
        pushDown(rt,mid-l+1,r-mid);
        if (L<=mid) update(L,R,C,l,mid,rt<<1);
        if (R>mid) update(L,R,C,mid+1,r,rt<<1|1);
        pushUp(rt);
    }

    static int query(int L,int R,int l,int r,int rt)
    {
        if (L<=l&&r<=R)
            return ans[rt];
        int mid=(l+r)>>1;
        pushDown(rt,mid-l+1,r-mid);//若更新只有点更新，不需要这句
        int ANS=0;
        if (L<=mid) ANS+=query(L,R,l,mid,rt<<1);
        if (R>mid) ANS+=query(L,R,mid+1,r,rt<<1|1);
        return ANS;
    }
}
