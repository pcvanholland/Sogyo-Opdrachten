package taipan.api;

import java.util.Arrays;

import com.mongodb.DB;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import org.bson.Document;

public class DataBase
{
    private static final String IP = "172.17.0.4";
    private static final int PORT = 27017;

    private MongoClient mongoClient;
    private DBCollection mongoCollection;

    /**
     * Opens a connection to the TaiPan MongoDB database.
     */
    protected void openConnection()
    {
        MongoCredential mongoCredential = MongoCredential.createCredential(
            "Java-API",
            "TaiPan",
            "API'sJava".toCharArray()
        );
        this.mongoClient = new MongoClient(
            new ServerAddress(IP, PORT),
            Arrays.asList(mongoCredential)
        );
        System.out.println("Connected to the database successfully.");

        DB mongoDB = this.mongoClient.getDB("TaiPan");
        this.mongoCollection = mongoDB.getCollection("players");
        System.out.println("Collection 'players' selected successfully.");
    }

    /**
     * Prints the entries in the "players" collection.
     */
    protected void printCollection()
    {
        this.openConnection();
        DBCursor cursor = this.mongoCollection.find();
        while (cursor.hasNext())
        {
            System.out.println(cursor.next());
        }
        this.closeConnection();
    }

    /**
     * Adds a player to the "player" collection.
     *
     * @param name {String} - The name of the Player to add.
     * @param password {String} - The password to use for the Player.
     *
     * @return {boolean} - Whether the addition was successfull.
     */
    protected boolean addPlayer(final String name, final String password)
    {
        this.openConnection();
        BasicDBObject query = new BasicDBObject();
        query.put("name", name);
        DBCursor cursor = this.mongoCollection.find(query);
        if (cursor.hasNext())
        {
            this.closeConnection();
            return false;
        }
        BasicDBObject document = new BasicDBObject()
            .append("name", name)
            .append("password", password);
        this.mongoCollection.insert(document);
        System.out.println("Document inserted successfully.");
        this.closeConnection();
        return true;
    }

    /**
     * Checks a username/password combination against the collection.
     *
     * @param name {String} - The username to check.
     * @param test {String} - The password to test.
     *
     * @return {boolean} - Whether the username/password combination
     *                      is present in the "players" database.
     */
    protected boolean verifyPassword(final String name, final String test)
    {
        this.openConnection();
        BasicDBObject query = new BasicDBObject();
        query.put("name", name);
        query.put("password", test);
        DBObject item = this.mongoCollection.findOne(query);
        this.closeConnection();
        return item != null;
    }

    protected void closeConnection()
    {
        this.mongoClient.close();
    }
}
