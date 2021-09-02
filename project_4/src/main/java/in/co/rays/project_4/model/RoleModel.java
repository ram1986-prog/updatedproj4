package in.co.rays.project_4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.project_4.bean.MarksheetBean;
import in.co.rays.project_4.bean.RoleBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.util.JDBCDataSource;

/**
 * The Class RoleModel.
 */
public class RoleModel {
	 
 	/** The log. */
 	private static Logger log = Logger.getLogger(RoleModel.class);
	
	/**
	 * Next PK.
	 *
	 * @return the integer
	 */
	public Integer nextPK() {
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(ID)from st_role");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;

	}

	/**
	 * Adds the.
	 *
	 * @param bean the bean
	 * @return the long
	 * @throws DuplicateRecordsException the duplicate records exception
	 * @throws ApplicationException the application exception
	 */
	public long add(RoleBean bean) throws DuplicateRecordException, ApplicationException {
		Connection conn = null;
		int pk = 0;
		
		 RoleBean duplicataRole = findByName(bean.getName());
	        // Check if create Role already exist
	        if (duplicataRole != null) {
	            throw new DuplicateRecordException("Role already exists");
	        }
	        
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into st_role value(?,?,?,?,?,?,?)");
			pk = nextPK();
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			System.out.println("role added");
			pstmt.close();
		} catch (Exception e) {
			  e.printStackTrace();
	            log.error("Database Exception..", e);
	            try {
	                conn.rollback();
	            } catch (Exception ex) {
	                throw new ApplicationException(
	                        "Exception : add rollback exception " + ex.getMessage());
	            }
	            throw new ApplicationException("Exception : Exception in add Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		  log.debug("Model add End");
		return pk;

	}

	/**
	 * Delete.
	 *
	 * @param bean the bean
	 */
	public void delete(RoleBean bean) {
		System.out.println("Role Model Delete method");
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM st_role where ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			System.out.println("role deleted");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	
	

	/**
	 * Update.
	 *
	 * @param bean the bean
	 */
	public void update(RoleBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("update st_role SET NAME=? WHERE ID=?");
			pstmt.setString(1, bean.getName());

			pstmt.setLong(2, bean.getId());
			pstmt.executeUpdate();
			System.out.println("role updated");
			pstmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Find by PK.
	 *
	 * @param pk the pk
	 * @return the role bean
	 */
	public RoleBean findByPK(long pk) {
		Connection conn = null;
		RoleBean bean = null;
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_ROLE WHERE ID=?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new RoleBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;

	}

	/*public List list() {
		ArrayList list = new ArrayList();
		Connection conn = null;
		StringBuffer sql = new StringBuffer("select * from st_role");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RoleBean bean = new RoleBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;

	}
*/

    /**
	 * List.
	 *
	 * @return the list
	 * @throws ApplicationException the application exception
	 */
	public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * Get List of Role with pagination.
     *
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of Role
     * @throws ApplicationException the application exception
     */

    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.debug("Model list Started");
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("select * from ST_ROLE");
        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + "," + pageSize);
        }

        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                RoleBean bean = new RoleBean();
                bean.setId(rs.getLong(1));
                bean.setName(rs.getString(2));
                bean.setDescription(rs.getString(3));
                bean.setCreatedBy(rs.getString(4));
                bean.setModifiedBy(rs.getString(5));
                bean.setCreatedDatetime(rs.getTimestamp(6));
                bean.setModifiedDatetime(rs.getTimestamp(7));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of Role");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model list End");
        return list;

    }
	
	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the role bean
	 */
	public RoleBean findByName(String name) {
		Connection conn = null;
		RoleBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_role where NAME=?");
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new RoleBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));

			}
			rs.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;

	}

	 /**
 	 * Search.
 	 *
 	 * @param bean the bean
 	 * @return the list
 	 * @throws ApplicationException the application exception
 	 */
 	public List search(RoleBean bean) throws ApplicationException {
	        return search(bean, 0, 0);
	    }
	
	/**
	 * Search.
	 *
	 * @param bean the bean
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the list
	 * @throws ApplicationException the application exception
	 */
	public List search(RoleBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM st_role WHERE 1=1");

        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND ID = " + bean.getId());
            }
            if (bean.getName() != null && bean.getName().length() > 0) {
                sql.append(" AND NAME like '" + bean.getName() + "%'");
            }
            if (bean.getDescription() != null
                    && bean.getDescription().length() > 0) {
                sql.append(" AND DESCRIPTION like '" + bean.getDescription()
                        + "%'");
            }

        }

        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;

            sql.append(" Limit " + pageNo + ", " + pageSize);
            // sql.append(" limit " + pageNo + "," + pageSize);
        }

        ArrayList list = new ArrayList();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new RoleBean();
                bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search Role");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model search End");
        return list;
    }
}
