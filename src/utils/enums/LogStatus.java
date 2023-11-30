package utils.enums;

public enum LogStatus {
    ONLINE,
    OFFLINE;

    /**
     * Toggles the log status.
     */
    public static LogStatus changeLogStatus(LogStatus logStatus) {
        if (logStatus == ONLINE) {
            return OFFLINE;
        }
        return ONLINE;
    }
}
