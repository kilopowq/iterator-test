package com.kilopo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public class CustomIterator implements Iterator<List<String>> {

    private Pattern startPattern;
    private Pattern endPattern;
    private Iterator<String> iterator;
    public List<String> storedList;

    public CustomIterator(Iterable<String> iterable, String startMarker, String endMarker) {
        this.iterator = iterable.iterator();
        this.startPattern = Pattern.compile(startMarker);
        this.endPattern = Pattern.compile(endMarker);
    }

    public CustomIterator(Iterable<String> iterable, Pattern startMarker, Pattern endMarker) {
        this.iterator = iterable.iterator();
        this.startPattern = startMarker;
        this.endPattern = endMarker;
    }

    public CustomIterator(Iterator<String> iterator, String startMarker, String endMarker) {
        this.iterator = iterator;
        this.startPattern = Pattern.compile(startMarker);
        this.endPattern = Pattern.compile(endMarker);
    }

    public CustomIterator(Iterator<String> iterator, Pattern startMarker, Pattern endMarker) {
        this.iterator = iterator;
        this.startPattern = startMarker;
        this.endPattern = endMarker;
    }

    @Override
    public boolean hasNext() {
        //Ліст спочатку налл для того, щоб виконувалось "hasNext() - при першому виклику повертає true."
        if (isNull(storedList)) {
            putNextBlock();
            return true;
        } else {
            return !storedList.isEmpty();
        }
    }

    @Override
    public List<String> next() {
        if (isNull(storedList)) {
            putNextBlock();
        }

        if (storedList.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            List<String> list = storedList;
            storedList = new ArrayList<>();
            putNextBlock();
            return list;
        }
    }

    private void putNextBlock() {
        boolean blockEnded = false;
        storedList = new ArrayList<>();

        while (iterator.hasNext()) {
            if (startPattern.matcher(iterator.next()).matches()) {
                while (iterator.hasNext()) {
                    String next = iterator.next();
                    if (endPattern.matcher(next).matches()) {
                        blockEnded = true;
                        break;
                    } else {
                        storedList.add(next);
                    }
                }
            }
            if (blockEnded) {
                break;
            }
        }

        if (!blockEnded) {
            storedList.clear();
        }
    }
}
