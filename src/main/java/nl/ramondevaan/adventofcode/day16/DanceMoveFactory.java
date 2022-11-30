package nl.ramondevaan.adventofcode.day16;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DanceMoveFactory {
  private final static String POSITION_1_TAG = "position1";
  private final static String POSITION_2_TAG = "position2";
  private final static String PARTNER_1_TAG = "partner1";
  private final static String PARTNER_2_TAG = "partner2";
  private final static String AMOUNT_TAG = "amount";

  private final static String EXCHANGE_REGEX =
      "x(?<" + POSITION_1_TAG + ">\\d+)/(?<" + POSITION_2_TAG + ">\\d+)";
  private final static String PARTNER_REGEX =
      "p(?<" + PARTNER_1_TAG + ">\\w+)/(?<" + PARTNER_2_TAG + ">\\w+)";
  private final static String SPIN_REGEX = "s(?<" + AMOUNT_TAG + ">\\d+)";

  private final static Pattern EXCHANGE_PATTERN = Pattern.compile(EXCHANGE_REGEX);
  private final static Pattern PARTNER_PATTERN = Pattern.compile(PARTNER_REGEX);
  private final static Pattern SPIN_PATTERN = Pattern.compile(SPIN_REGEX);

  public DanceMove parse(String input) {
    Matcher matcher;

    if ((matcher = EXCHANGE_PATTERN.matcher(input)).matches()) {
      return new Exchange(
          Integer.parseInt(matcher.group(POSITION_1_TAG)),
          Integer.parseInt(matcher.group(POSITION_2_TAG))
      );
    } else if ((matcher = PARTNER_PATTERN.matcher(input)).matches()) {
      return new Partner(
          new Identifier(matcher.group(PARTNER_1_TAG)),
          new Identifier(matcher.group(PARTNER_2_TAG))
      );
    } else if ((matcher = SPIN_PATTERN.matcher(input)).matches()) {
      return new Spin(
          Integer.parseInt(matcher.group(AMOUNT_TAG))
      );
    } else {
      throw new IllegalArgumentException(String.format(
          "Could not parse input \"%s\"",
          input
      ));
    }
  }
}
