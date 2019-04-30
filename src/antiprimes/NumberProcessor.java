package antiprimes;

public class NumberProcessor extends Thread{
    private Number out;
    private Number tmp;
    private boolean accept;
    private Observer obs;


    public NumberProcessor(Observer obs) {
        this.accept = true;
        this.obs = obs;
    }

    @Override
    public void run(){
        while(true){
            checkAccept();
            out = AntiPrimes.nextAntiPrimeAfter(tmp);
            acceptRequests();
            obs.update();
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
