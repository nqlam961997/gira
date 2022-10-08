package dev.lam.gira.user.model;

import lombok.experimental.UtilityClass;

public class UserEntity {
    @UtilityClass
    public class UserMappedUserGroup {
        public static final String SERVICE_MAPPED_USER = "users";
        public static final String JOIN_TABLE = "G_USER_USER_GROUP";
        public static final String JOIN_TABLE_USER_ID = "G_USER_ID";
        public static final String JOIN_TABLE_USER_GROUP_ID = "G_USER_GROUP_ID";
    }

    @UtilityClass
    public class User {
        public static final String TABLE_NAME = "G_USER";

        public static final String EMAIL = "G_EMAIL";
        public static final String PASSWORD = "G_PASSWORD";

        public static final String USERNAME = "G_USERNAME";

        public static final String DISPLAY_NAME = "G_DISPLAY_NAME";

        public static final String FIRST_NAME = "G_FIRST_NAME";

        public static final String LAST_NAME = "G_LAST_NAME";

        public static final String STATUS = "G_STATUS";

        public static final String DEPARTMENT = "G_DEPARTMENT";

        public static final String AVATAR = "G_AVATAR";
    }

    @UtilityClass
    public class UserGroup {
        public static final String TABLE_NAME = "G_USER_GROUP";
        public static final String NAME = "G_NAME";
        public static final String DESCRIPTION = "G_DESCRIPTION";
        public static final String CODE = "G_CODE";

    }
}
