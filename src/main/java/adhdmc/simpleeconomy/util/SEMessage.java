package adhdmc.simpleeconomy.util;

public enum SEMessage {

    PLUGIN_PREFIX("<white>[<green>Economy</green>]</white>"),
    CURRENCY("dollars"),
    PAY_USAGE("<gray>/pay <dark_gray><</dark_gray>username<dark_gray>></dark_gray> <dark_gray><</dark_gray>amount<dark_gray>></dark_gray></gray>"),
    ONLY_PLAYER_COMMAND("<red>Sorry! That command can only be run by a player!"),
    ERROR_PLAYER_NOT_ACCEPTING_PAYMENT("<plugin_prefix> <gray>Looks like <target> isn't accepting payment at this time. Maybe later"),
    ERROR_PLAYER_INVALID("<plugin_prefix> <red>ERROR: There was no player found by the name of <target>, please check your spelling and try again. Command usage: <usage>"),
    ERROR_INCOMPLETE_COMMAND("<plugin_prefix> <red>ERROR: Incomplete command. Command usage: <usage>"),
    ERROR_PAYMENT_MUST_BE_NUMBER("<plugin_prefix> <red>ERROR: You must provide a positive number. Command usage: <usage>"),
    ERROR_NOT_ENOUGH_FUNDS("<plugin_prefix> <red>Sorry! You don't have <amount> to send."),
    ERROR_PAY_NEGATIVE("<plugin_prefix> <red>Sorry, you cannot pay someone a negative amount."),
    YOU_PAID_TARGET("<plugin_prefix> <green>You paid <yellow><target></yellow> <value> <currency>"),
    INITIATOR_PAID_YOU("<plugin_prefix> <yellow><initiator></yellow> paid you <value> <currency>")


    ;
    String message;

    SEMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
