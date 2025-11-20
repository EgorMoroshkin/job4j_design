package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

import static ru.job4j.io.Search.validateParams;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String rls = values.get(key);
        if (rls == null) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return rls;
    }

    private void parse(String[] args) {
        for (String str : args) {
            int index = str.strip().indexOf("=");
            validate(str, index);
            String key = str.substring(1, index);
            String value = str.substring(index + 1);
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private void validate(String arg, int index) {
        if (arg.charAt(0) != '-') {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not start with a '-' character", arg));
        }
        if (index == -1) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain an equal sign", arg));
        }
        if (index == 1) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a key", arg));
        }
        if (index == arg.length() - 1) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a value", arg));
        }
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("encoding"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
