package in.co.rays.project_4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import in.co.rays.project_4.bean.DropdownListBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DatabaseException;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.JDBCDataSource;

/**
 * The Class BaseModel.
 */
public abstract class BaseModel implements DropdownListBean {
	
	 /** The id. */
 	protected long id;

	    /**
	     * User name that creates this record.
	     */
	    protected String createdBy;

	    /**
	     * User name that modifies this record.
	     */
	    protected String modifiedBy;

	    /** Created timestamp of record. */
	    protected Timestamp createdDatetime;

	    /** Modified timestamp of record. */
	    protected Timestamp modifiedDatetime;

	    /**
    	 * accessor methods.
    	 *
    	 * @return the id
    	 */
	    public long getId() {
	        return id;
	    }

	    /**
    	 * Sets the id.
    	 *
    	 * @param id the new id
    	 */
    	public void setId(long id) {
	        this.id = id;
	    }

	    /**
    	 * Gets the created datetime.
    	 *
    	 * @return the created datetime
    	 */
    	public Timestamp getCreatedDatetime() {
	        return createdDatetime;
	    }

	    /**
    	 * Sets the created datetime.
    	 *
    	 * @param createdDatetime the new created datetime
    	 */
    	public void setCreatedDatetime(Timestamp createdDatetime) {
	        this.createdDatetime = createdDatetime;
	    }

	    /**
    	 * Gets the modified datetime.
    	 *
    	 * @return the modified datetime
    	 */
    	public Timestamp getModifiedDatetime() {
	        return modifiedDatetime;
	    }

	    /**
    	 * Sets the modified datetime.
    	 *
    	 * @param modifiedDatetime the new modified datetime
    	 */
    	public void setModifiedDatetime(Timestamp modifiedDatetime) {
	        this.modifiedDatetime = modifiedDatetime;
	    }

	    /**
    	 * Gets the created by.
    	 *
    	 * @return the created by
    	 */
    	public String getCreatedBy() {
	        return createdBy;
	    }

	    /**
    	 * Sets the created by.
    	 *
    	 * @param createdBy the new created by
    	 */
    	public void setCreatedBy(String createdBy) {
	        this.createdBy = createdBy;
	    }

	    /**
    	 * Gets the modified by.
    	 *
    	 * @return the modified by
    	 */
    	public String getModifiedBy() {
	        return modifiedBy;
	    }

	    /**
    	 * Sets the modified by.
    	 *
    	 * @param modifiedBy the new modified by
    	 */
    	public void setModifiedBy(String modifiedBy) {
	        this.modifiedBy = modifiedBy;
	    }

	    /**
    	 * Compares IDs ( Primary Key). If keys are equals then objects are equals.
    	 *
    	 * @param next the next
    	 * @return the int
    	 */
	    public int compareTo(BaseModel next) {
	        return (int) (id - next.getId());
	    }


	    /**
    	 * Next PK.
    	 *
    	 * @return the long
    	 * @throws DatabaseException the database exception
    	 */
    	public long nextPK() throws DatabaseException {
	   
	        Connection conn = null;
	        long pk = 0;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM " + getTableName());
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                pk = rs.getInt(1);
	            }
	            rs.close();
	        } catch (Exception e) {
	            
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        
	        return pk + 1;
	    }

	    
	    /**
    	 * Gets the table name.
    	 *
    	 * @return the table name
    	 */
    	public abstract String getTableName();

	    
	    /**
    	 * Update created info.
    	 *
    	 * @throws ApplicationException the application exception
    	 */
    	public void updateCreatedInfo() throws ApplicationException {

	     

	        Connection conn = null;

	        String sql = "UPDATE " + getTableName()
	                + " SET CREATED_BY=?, CREATED_DATETIME=? WHERE ID=?";
	        

	        try {
	            conn = JDBCDataSource.getConnection();
	            conn.setAutoCommit(false); // Begin transaction
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, createdBy);
	            pstmt.setTimestamp(2, DataUtility.getCurrentTimestamp());
	            pstmt.setLong(3, id);

	            pstmt.executeUpdate();
	            conn.commit(); // End transaction
	            pstmt.close();
	        } catch (SQLException e) {
	           e.printStackTrace();
	            throw new ApplicationException(e.toString());
	        } catch (Exception e) {
	            
	            e.printStackTrace();
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        
	    }

	   
	    /**
    	 * Update modified info.
    	 *
    	 * @throws Exception the exception
    	 */
    	public void updateModifiedInfo() throws Exception {

	        //log.debug("Model update Started");
	        Connection conn = null;

	        String sql = "UPDATE " + getTableName()
	                + " SET MODIFIED_BY=?, MODIFIED_DATETIME=? WHERE ID=?";

	        try {
	            conn = JDBCDataSource.getConnection();
	            conn.setAutoCommit(false); // Begin transaction
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, modifiedBy);
	            pstmt.setTimestamp(2, DataUtility.getCurrentTimestamp());
	            pstmt.setLong(3, id);
	            pstmt.executeUpdate();
	            conn.commit(); // End transaction
	            pstmt.close();
	        } catch (SQLException e) {
	           e.printStackTrace();
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        
	    }

	   
	    /**
    	 * Populate model.
    	 *
    	 * @param <T> the generic type
    	 * @param model the model
    	 * @param rs the rs
    	 * @return the t
    	 * @throws SQLException the SQL exception
    	 */
    	protected <T extends BaseModel> T populateModel(T model, ResultSet rs)
	            throws SQLException {
	        model.setId(rs.getLong("ID"));
	        model.setCreatedBy(rs.getString("CREATED_BY"));
	        model.setModifiedBy(rs.getString("MODIFIED_BY"));
	        model.setCreatedDatetime(rs.getTimestamp("CREATED_DATETIME"));
	        model.setModifiedDatetime(rs.getTimestamp("MODIFIED_DATETIME"));
	        return model;
	    }
	}

	


