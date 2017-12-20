package nl.ramondevaan.adventofcode.day18;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionFactory {
    private final static String REGISTER_TAG = "register";
    private final static String VALUE_1_TAG    = "value1";
    private final static String VALUE_2_TAG    = "value2";

    private final static String SET_REGEX      =
            "set (?<" + REGISTER_TAG + ">\\w+) (?<" + VALUE_1_TAG + ">(-?\\d+)|(\\w+))";
    private final static String ADD_REGEX      =
            "add (?<" + REGISTER_TAG + ">\\w+) (?<" + VALUE_1_TAG + ">(-?\\d+)|(\\w+))";
    private final static String MULTIPLY_REGEX =
            "mul (?<" + REGISTER_TAG + ">\\w+) (?<" + VALUE_1_TAG + ">(-?\\d+)|(\\w+))";
    private final static String MODULO_REGEX   =
            "mod (?<" + REGISTER_TAG + ">\\w+) (?<" + VALUE_1_TAG + ">(-?\\d+)|(\\w+))";
    private final static String JUMP_REGEX     =
            "jgz (?<" + VALUE_1_TAG + ">\\w+) (?<" + VALUE_2_TAG + ">(-?\\d+)|(\\w+))";
    private final static String SOUND_REGEX    =
            "snd (?<" + VALUE_1_TAG + ">\\w+)";
    private final static String RECOVER_REGEX  =
            "rcv (?<" + VALUE_1_TAG + ">\\w+)";

    private final static Pattern SET_PATTERN      = Pattern.compile(SET_REGEX);
    private final static Pattern ADD_PATTERN      = Pattern.compile(ADD_REGEX);
    private final static Pattern MULTIPLY_PATTERN = Pattern.compile(MULTIPLY_REGEX);
    private final static Pattern MODULE_PATTERN   = Pattern.compile(MODULO_REGEX);
    private final static Pattern SOUND_PATTERN    = Pattern.compile(SOUND_REGEX);
    private final static Pattern RECOVER_PATTERN  = Pattern.compile(RECOVER_REGEX);
    private final static Pattern JUMP_PATTERN     = Pattern.compile(JUMP_REGEX);

    public Instruction parse(String s) {
        Matcher m;

        if((m = SET_PATTERN.matcher(s)).matches()) {
            return new Set(
                    new Identifier(m.group(REGISTER_TAG)),
                    Value.parse(m.group(VALUE_1_TAG))
            );
        } else if((m = ADD_PATTERN.matcher(s)).matches()) {
            return new Add(
                    new Identifier(m.group(REGISTER_TAG)),
                    Value.parse(m.group(VALUE_1_TAG))
            );
        } else if((m = MULTIPLY_PATTERN.matcher(s)).matches()) {
            return new Multiply(
                    new Identifier(m.group(REGISTER_TAG)),
                    Value.parse(m.group(VALUE_1_TAG))
            );
        } else if((m = MODULE_PATTERN.matcher(s)).matches()) {
            return new Modulo(
                    new Identifier(m.group(REGISTER_TAG)),
                    Value.parse(m.group(VALUE_1_TAG))
            );
        } else if((m = JUMP_PATTERN.matcher(s)).matches()) {
            return new Jump(
                    Value.parse(m.group(VALUE_1_TAG)),
                    Value.parse(m.group(VALUE_2_TAG))
            );
        } else if((m = SOUND_PATTERN.matcher(s)).matches()) {
            return new Snd(
                    Value.parse(m.group(VALUE_1_TAG))
            );
        } else if((m = RECOVER_PATTERN.matcher(s)).matches()) {
            return new Rcv(
                    Value.parse(m.group(VALUE_1_TAG))
            );
        }

        throw new IllegalArgumentException(String.format(
                "Could not parse \"%s\" to an instruction.",
                s
        ));
    }
}
