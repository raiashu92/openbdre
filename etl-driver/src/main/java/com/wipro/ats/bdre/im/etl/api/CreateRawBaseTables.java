/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wipro.ats.bdre.im.etl.api;

import com.wipro.ats.bdre.im.etl.api.base.ETLBase;
import com.wipro.ats.bdre.im.etl.api.exception.ETLException;
import org.apache.commons.cli.CommandLine;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by vishnu on 12/14/14.
 * Modified by Arijit
 */
public class CreateRawBaseTables extends ETLBase {
    private static final Logger LOGGER = Logger.getLogger(CreateRawBaseTables.class);
    private static final String[][] PARAMS_STRUCTURE = {
            {"p", "process-id", " Process id of ETLDriver"}
    };

    public void execute(String[] params) {

        CommandLine commandLine = getCommandLine(params, PARAMS_STRUCTURE);
        String processId = commandLine.getOptionValue("process-id");

        init(processId);
        //Getting stage table information
        String rawTableName = getRawTable().getTableName();
        String rawDbName = getRawTable().getDbName();
        String rawTableDdl = getRawTable().getDdl();
        //Getting Stage view information
        String rawViewName = getRawView().getTableName();
        String rawViewDbName = getRawView().getDbName();
        String rawViewDdl = getRawView().getDdl();
        //Getting core table information
        String baseTableName = getBaseTable().getTableName();
        String baseTableDbName = getBaseTable().getDbName();
        String baseTableDdl = getBaseTable().getDdl();
        //Now create the tables/view if not exists.
        checkAndCreateRawTable(rawDbName, rawTableName, rawTableDdl);
        checkAndCreateRawView(rawViewDbName, rawViewName, rawViewDdl);
        checkAndCreateBaseTable(baseTableDbName, baseTableName, baseTableDdl);
    }

    private void checkAndCreateRawTable(String dbName, String tableName, String ddl) {
        try {
            LOGGER.debug("Reading Hive Connection details from Properties File");
            Connection con = getHiveJDBCConnection(dbName);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE '" + tableName + "'");
            if (!rs.next()) {
                LOGGER.info("Raw table does not exist Creating table " + tableName);
                LOGGER.info("Creating stage table using "+ddl);
                stmt.executeUpdate(ddl);
            }
            LOGGER.debug("Inserting data into the table");
            stmt.close();
            con.close();
            LOGGER.info("Raw load completed.");

        } catch (Exception e) {
            LOGGER.error("Error In RawLoad" + e);
            throw new ETLException(e);
        }

    }

    private void checkAndCreateRawView(String dbName, String stageViewName, String ddl) {
        try {

            Connection con = getHiveJDBCConnection(dbName);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE '" + stageViewName + "'");
            if (!rs.next()) {
                LOGGER.debug("View does not exist. Creating View " + stageViewName);
                stmt.executeUpdate(ddl);
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
            LOGGER.error("Error " + e);
            throw new ETLException(e);
        }
    }

    private void checkAndCreateBaseTable(String dbName, String baseTable, String ddl) {
        try {

            Connection con = getHiveJDBCConnection(dbName);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE '" + baseTable + "'");
            if (!rs.next()) {
                LOGGER.info("Base table does not exist.Creating Table " + baseTable);
                stmt.executeUpdate(ddl);
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
            LOGGER.error("Error " + e);
            throw new ETLException(e);
        }
    }
}