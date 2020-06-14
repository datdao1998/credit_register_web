package common.constant;

public class FPConstant {

    public static class Url {
        public static final String BASE_WEB_URL = "http://localhost:8080/CreditRegister/";
        public static final String BASE_URL_API_SLOT = "http://localhost:8080/CreditRegister/api/slots/";
        public static final String BASE_URL_API_SUBJECT = "http://localhost:8080/CreditRegister/api/subjects/";
        public static final String BASE_URL_API_LGD = "http://localhost:8080/CreditRegister/api/lgds/";
        public static final String BASE_URL_API_CLASS = "http://localhost:8080/CreditRegister/api/classes/";
        public static final String BASE_URL_API_USER = "http://localhost:8080/CreditRegister/api/users/";
        public static final String BASE_URL_API_STUDENT = "http://localhost:8080/CreditRegister/api/students/";
        public static final String BASE_URL_API_TEACH_FORM = "http://localhost:8080/CreditRegister/api/teach_forms/";
        public static final String BASE_URL_API_LEARN_FORM = "http://localhost:8080/CreditRegister/api/learn_forms/";
    }

    public static class PickingStatus {
        public static final String PICKKED = "PICKED";
        public static final String NOT_PICK = "NOT_PICK";
    }

    public static class FormStatus {
        public static final String LEARNING = "LEARNING";
        public static final String LEARNED = "LEARNED";
        public static final String CANCELED = "CANCELED";
    }

    public static class AccountRole {
        public static final String ACCOUNT_ROLE_TEACHER = "GV";
        public static final String ACCOUNT_ROLE_STUDENT = "SV";
        public static final String ACCOUNT_ROLE_ADMIN = "QL";
    }

}
