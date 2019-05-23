package entity;

public class Map extends Entity {

    /** The id. */
    private int			id;

    /** The message. */
    private String	schema;

    /**
     * Instantiates a new hello world.
     *
     * @param id
     *          the id
     * @param schema
     *          the schema
     */
    public Map(final int id, final String schema) {
        this.setId(id);
        this.setMessage(schema);
    }

    /**
     * Instantiates a new hello world.
     */
    public Map() {
        this(0, "");
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *          the new id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Gets the schema.
     *
     * @return the schema
     */
    public String getSchema() {
        return this.schema;
    }

    /**
     * Sets the message.
     *
     * @param schema
     *          the new message
     */
    public void setMessage(final String schema) {
        this.schema = schema;
    }


}
