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

package com.wipro.ats.bdre.lineage;

import com.wipro.ats.bdre.GetLineageNodeByColName;
import com.wipro.ats.bdre.GetNodeIdForLineageRelation;
import com.wipro.ats.bdre.md.dao.jpa.LineageNode;
import com.wipro.ats.bdre.md.dao.jpa.LineageRelation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by AshutoshRai on 1/20/16.
 */
public class GetDotForTable {
    protected static final Logger logger = LoggerFactory.getLogger(GetDotForTable.class);

    public static void main(String args[]){

        GetDotForTable getDotForTable = new GetDotForTable();
        GetNodeIdForLineageRelation getNodeIdForLineageRelation = new GetNodeIdForLineageRelation();
        //get elements of LR table
        List<LineageRelation> lineageRelationList;
        //get the nodes which have the same DISPLAY_NAME as that of the string provided
        List<LineageNode> lineageNodeList = getDotForTable.getLineageNodeNodeId(args);

        Pattern tableNameSearch = Pattern.compile("\"(.*?)\"");

        String srcTableName = args[2];
        String targetTableName = null;
        String relationDot;
        List<String> matchedStrings = new ArrayList<>();
        GetLineageNodeByColName getLineageNodeByColName = new GetLineageNodeByColName();
        LineageNode srcTableNode = getLineageNodeByColName.getTableDotFromTableName(srcTableName);
        //List<String> nodeIds = new ArrayList<>();
        //List<String> nodeDotString = new ArrayList<>();

        //compare the list of Nodes got from LN table with LR table to get the required node & store its node id & dot string

            for(LineageNode lineageNode:lineageNodeList) {
                System.out.println("outside if");
                /*System.out.println("LN: "+lineageNode.getNodeId());
                System.out.println("LR: "+lineageRelation.getDotString());
                System.out.println("LR: "+lineageRelation.getLineageNodeBySrcNodeId());
                if(lineageNode.getNodeId().equals(lineageRelation.getLineageNodeBySrcNodeId())) { */

                lineageRelationList = getNodeIdForLineageRelation.execute(lineageNode.getNodeId());
                if(lineageRelationList.size()!=0) {
                    //nodeIds.add(lineageNode.getNodeId());                                         //add the matched Node's nodeId to list
                    //nodeDotString.add(lineageRelationList.get(0).getDotString());                 //add the corresponding Dot String to list

                    relationDot = lineageRelationList.get(0).getDotString();                        //search for target table name in the DOT string
                    String targetTableNode = getLineageNodeByColName.getTableDotFromNodeId(lineageRelationList.get(0).getLineageNodeByTargetNodeId());
                    /*Matcher tableMatch = tableNameSearch.matcher(relationDot);
                    while(tableMatch.find()) {
                        matchedStrings.add(tableMatch.group(1));
                    }
                    if (matchedStrings.get(0).endsWith(srcTableName.toUpperCase())) {
                        targetTableName = matchedStrings.get(1);
                    } else if (matchedStrings.get(1).endsWith(srcTableName.toUpperCase())) {
                        targetTableName = matchedStrings.get(0);
                    }
                    targetTableName = targetTableName.substring(targetTableName.indexOf(".")+1);
                    LineageNode targetTableNode = getLineageNodeByColName.getTableDotFromTableName(targetTableName); */


                    logger.info("relation DOT: \n" + relationDot);
                    LineageMain lineageMain = new LineageMain();
                    lineageMain.generateLineageDot(relationDot, srcTableNode, targetTableNode);                         //call lineage main to create the DOT
                    //break;
                }

            }
    }

    private List<LineageNode> getLineageNodeNodeId (String params[]) {
        GetLineageNodeByColName getLineageNodeByColName = new GetLineageNodeByColName();
        List<LineageNode> lineageNodeList = getLineageNodeByColName.execute(params);
        for(LineageNode lineageNode:lineageNodeList){
            logger.info("Column name: "+lineageNode.getDisplayName()+" Node id: "+lineageNode.getNodeId());
        }
        return lineageNodeList;
    }
}