package mmsquare.hibernate.dialect;

import org.hibernate.dialect.PostgreSQLDialect;

public class PostgreSQL82Dialect extends PostgreSQLDialect {


    public PostgreSQL82Dialect() {
        super();
    }

    public boolean supportsInsertSelectIdentity() {
        return true;
    }

    public String appendIdentitySelectToInsert(String insertString) {
        return insertString + " RETURNING id";
    }

}
