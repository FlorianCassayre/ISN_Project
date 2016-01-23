package me.cassayre.florian.ISN;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Test
{
    public static void main(String[] args)
    {
        long s1 = -2242547518357798464L;
        long s2 = -2063810908146531904L;

        Random random = new Random(s2);
        System.out.println(random.nextInt());
        Random random1 = new Random(s2);
        System.out.println(random1.nextInt());

        System.out.println(get(s1, 545));
        System.out.println(get(s2, 545));

        System.out.println();

        System.out.println(0x5DEECE66DL + 0xBL);

        System.out.println(((s1 * 0x5DEECE66DL + 0xBL) & (1L << 48) - 1)   + "   " +   ((s2 * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1)));

        System.out.println(((s1) & 281474976710655L) + "  " + ((s2) & 281474976710655L));

        System.out.println(Long.toBinaryString(s1) + " " + Long.toBinaryString(s2));
        System.out.println(Long.toBinaryString(281474976710655L));

        long l = parseLong("1110100011100000110111101110100111100000010110011000000111000000", 2);

        System.out.println(l);
        System.out.println(((s1) & 281474976710655L) + "  " + ((l) & 281474976710655L));
    }

    /**
     *
     * (seed1 * 0x5DEECE66DL + 0xBL) & (1L << 48) - 1   ==    (seed2 * 0x5DEECE66DL + 0xBL) & (1L << 48) - 1
     *
     *
     * (seed1 * 25214903928) & 281474976710655
     *
     * @param seed1
     * @param bits
     * @return
     */

    public static int get(long seed1, int bits)
    {
        long oldseed, nextseed;
        AtomicLong seed = new AtomicLong(seed1);
        do {
            oldseed = seed.get();
            nextseed = (oldseed * 0x5DEECE66DL + 0xBL) & (1L << 48) - 1;
        } while (!seed.compareAndSet(oldseed, nextseed));
        return (int)(nextseed >>> (48 - bits));
    }

    private static long parseLong(String s, int base) {
        return new BigInteger(s, base).longValue();
    }
}
