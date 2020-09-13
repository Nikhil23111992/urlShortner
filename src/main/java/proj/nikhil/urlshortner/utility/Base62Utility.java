package proj.nikhil.urlshortner.utility;

import org.springframework.stereotype.Component;

@Component
public class Base62Utility
{

    private final char[] digitsChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private final int BASE = digitsChar.length;

    private final int FAST_SIZE = 'z';

    private final int[] digitsIndex = new int[FAST_SIZE + 1];

    {
        for (int i = 0; i < FAST_SIZE; i++)
        {
            digitsIndex[i] = -1;
        }
        for (int i = 0; i < BASE; i++)
        {
            digitsIndex[digitsChar[i]] = i;
        }
    }

    public long decode(String s)
    {
        long result = 0L;
        long multiplier = 1;
        for (int pos = s.length() - 1; pos >= 0; pos--)
        {
            int index = getIndex(s, pos);
            result += index * multiplier;
            multiplier *= BASE;
        }
        return result;
    }

    public String encode(long number)
    {
        if (number < 0)
            throw new IllegalArgumentException("Number(Base62) must be positive: " + number);
        if (number == 0)
            return "0";
        StringBuilder buf = new StringBuilder();
        while (number != 0)
        {
            buf.append(digitsChar[(int) (number % BASE)]);
            number /= BASE;
        }
        return buf.reverse().toString();
    }

    private int getIndex(String s, int pos)
    {
        char c = s.charAt(pos);
        if (c > FAST_SIZE)
        {
            throw new IllegalArgumentException("Unknow character for Base62: " + s);
        }
        int index = digitsIndex[c];
        if (index == -1)
        {
            throw new IllegalArgumentException("Unknow character for Base62: " + s);
        }
        return index;
    }
}
