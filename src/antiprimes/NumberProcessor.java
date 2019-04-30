package antiprimes;

public class NumberProcessor extends Thread{
    private Number out;
    private Number tmp;
    private boolean accept;
    private Observer obs1;
    private Observer obs2;


    public NumberProcessor(Observer obs1, Observer obs2) {
        this.accept = true;
        this.obs1 = obs1;
        this.obs2 = obs2;
    }

    @Override
    public void run(){
        while(true){
            checkAccept();
            out = AntiPrimes.nextAntiPrimeAfter(tmp);
            obs2.update();
            obs1.update();
            acceptRequests();
        }
    }

    synchronized public void nextAntiPrime(Number n){
        tmp = n;
        accept = false;
        notify();
    }

    synchronized public Number getNextToProcessor(){
        return out;
    }

    synchronized private void acceptRequests(){
        accept = true;
    }

    synchronized private void checkAccept(){
        while(accept){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
