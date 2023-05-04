package ir.brandimo.training.shop.util;

public interface Keys {
    public interface ApiPath {
        public static final String AdminPath = "admin/";
        public static final String AuthApiPath = "v1/auth";
        public static final String CategoryApiPath = AdminPath + "v1/categories";
        public static final String VariationApiPath = AdminPath + "v1/variations";
        public static final String UserApiPath = AdminPath + "v1/users";
        public static final String PermissionApiPath = AdminPath + "v1/permissions";
        public static final String RoleApiPath = AdminPath + "v1/roles";
        public static final String ProductApiPath = AdminPath + "v1/products";
    }
}
