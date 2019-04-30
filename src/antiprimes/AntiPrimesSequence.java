package antiprimes;

import java.util.ArrayList;
import java.util.List;


/**
 * Represent the sequence of antiprimes found so far.
 */
public class AntiPrimesSequence implements Observer{

    /**
     * The numbers in the sequence.
     */
    private List<Number> antiPrimes;
    private NumberProcessor processor;
    private Observer obs;

    public NumberProcessor getProcessor() {
        return processor;
    }

    /**

     * Create a new sequence containing only the first antiprime (the number '1').
     */
    public AntiPrimesSequence() {
        this.reset();
    }

    public void setObserver(Observer obs){
        this.obs = obs;
        processor = new NumberProcessor(this.obs, this);
        processor.start();
    }

    /**
     * Clear the sequence so that it contains only the first antiprime (the number '1').
     */
    public void reset() {
        antiPrimes = new ArrayList<>();
        antiPrimes.add(new Number(1, 1));
    }

    /**
     * Find a new antiprime and add it to the sequence.
     */
    public void computeNext() {
        processor.nextAntiPrime(getLast());
    }


    /**
     * Return the last antiprime found.
     */
    public Number getLast() {
        int n = antiPrimes.size();
        return antiPrimes.get(n - 1);
    }

    /**
     * Return the last k antiprimes found.
     */
    public List<Number> getLastK(int k) {
        int n = antiPrimes.size();
        if (k > n)
            k = n;
        return antiPrimes.subList(n - k, n);
    }

    @Override
    public void update() {
        antiPrimes.add(processor.getNextToProcessor());
    }

}
