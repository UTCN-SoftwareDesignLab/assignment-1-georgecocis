package Database;

public class ConnectionFactory {
    public ConnectionWrapper getConnectionWrapper(boolean test) {
        if (test) {
            return new ConnectionWrapper(Constants.Schemas.TEST);
        }
        return new ConnectionWrapper(Constants.Schemas.PRODUCTION);
    }
}
