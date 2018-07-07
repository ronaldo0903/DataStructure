package titi.Learning.java.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.LinkedList;


public class ReferenceTest {

	private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<VeryBig>();
    public static void checkQueue(){
        Reference<? extends VeryBig> inq = rq.poll();
        if(inq!=null){
            System.out.println("In queue[1]:"+inq.getClass().getSimpleName());
            System.out.println("In queue[2]:"+inq.get());
        }
    }

    public static void main(String[] args) {
        int size = 10;
        /*
         * SoftReference:���ڴ治��ʱ�Ż�������������ö���
         * */
        LinkedList<SoftReference<VeryBig>> sa = new  LinkedList<SoftReference<VeryBig>>();
        for(int i=0;i<size;i++){
            sa.add(new SoftReference(new VeryBig("Soft "+i),rq));
            System.out.println("Just created[Soft]: "+sa.getLast().get());
            checkQueue();//һֱΪ��
        }
        /*
         * WeakReference:��GC����ֻ���������õĶ����������������
         * */
        LinkedList<VeryBigWeakReference> wa = new  LinkedList<VeryBigWeakReference>();
        for(int i=0;i<size;i++){
            wa.add(new VeryBigWeakReference(new VeryBig("Weak "+i),rq));
            System.out.println("Just created[Weak]: "+wa.getLast().get());
            checkQueue();
        }

        SoftReference<VeryBig> sf = new SoftReference<VeryBig>(new VeryBig("Soft "));
        VeryBigWeakReference wf = new VeryBigWeakReference(new VeryBig("Weak"), rq);

        System.gc();//��ʾ�Ľ����������գ�ʲôʱ��ִ�о���JVM����

        LinkedList<PhantomReference<VeryBig>> pa = new  LinkedList<PhantomReference<VeryBig>>();
        for(int i=0;i<size;i++){
            pa.add(new PhantomReference(new VeryBig("Phantom "+i),rq));
            System.out.println("Just created[Phantom]: "+pa.getLast());
            checkQueue();
        }       
    }

}
