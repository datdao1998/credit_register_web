package common.exception;
// Author: anhnv

import common.constant.FPMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FPException extends Exception {

    private String message;

    public static class NotFoundEntityException extends FPException {
        public NotFoundEntityException() {
            super(FPMessage.NOT_FOUND_ENTITY);
        }
    }

    public static class DuplicateEntityException extends FPException {
        public DuplicateEntityException() {
            super(FPMessage.DUPLICATE_ENTITY);
        }
    }

    public static class DateFormatException extends FPException {
        public DateFormatException() {
            super(FPMessage.INVALID_DATE_FORMAT);
        }
    }

    public static class StatusInvalidException extends FPException {
        public StatusInvalidException() { super(FPMessage.STATUS_INVALID); }
    }
}
