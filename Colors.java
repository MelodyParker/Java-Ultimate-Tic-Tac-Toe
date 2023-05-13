class Colors {
  public static final String ESCAPE_CODE = "\u001b";
  public static final String DEFAULT = "[0m";
  public static final String BLACK = "[30m";
  public static final String DARK_RED = "[31m";
  public static final String DARK_GREEN = "[32m";
  public static final String DARK_YELLOW = "[33m";
  public static final String DARK_BLUE = "[34m";
  public static final String DARK_MAGENTA = "[35m";
  public static final String DARK_CYAN = "[36m";
  public static final String DARK_WHITE = "[37m";
  public static final String BRIGHT_BLACK = "[90m";
  public static final String BRIGHT_RED = "[91m";
  public static final String BRIGHT_GREEN = "[92m";
  public static final String BRIGHT_YELLOW = "[93m";
  public static final String BRIGHT_BLUE = "[94m";
  public static final String BRIGHT_MAGENTA = "[95m";
  public static final String BRIGHT_CYAN = "[96m";
  public static final String WHITE = "[97m";
  
  public static String colorString(String str, String col) {
    return ESCAPE_CODE + col + str + ESCAPE_CODE + WHITE;
  }
}