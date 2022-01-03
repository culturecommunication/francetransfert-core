package fr.gouv.culture.francetransfert.enums;

public enum RedisQueueEnum {
    MAIL_QUEUE("email-notification-queue"),
    ZIP_QUEUE("zip-worker-queue"),
    DOWNLOAD_QUEUE("download-notification-queue"),
	TEMP_DATA_CLEANUP_QUEUE("redis-temp-data-cleanup-queue"),
    CONFIRMATION_CODE_MAIL_QUEUE("confirmation-code-mail-queue"),
    SATISFACTION_QUEUE("satisfaction-queue"),
    STAT_QUEUE("stat-queue"),
    SEQUESTRE_QUEUE("sequestre-queue");

    private String value;

    RedisQueueEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
