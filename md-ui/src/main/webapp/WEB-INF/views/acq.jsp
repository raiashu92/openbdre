<%@ taglib prefix="security"
	   uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Bigdata Ready Enterprise</title>
	<!-- Include one of jTable styles. -->

	<link href="../css/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="../css/jtables-bdre.css" rel="stylesheet" type="text/css" />
	<link href="../css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />

	<!-- Include jTable script file. -->
	<script src="../js/jquery.min.js" type="text/javascript"></script>
	<script src="../js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
	<script src="../js/jquery.jtable.js" type="text/javascript"></script>

	<script type="text/javascript">
		    $(document).ready(function () {
	    $('#Container').jtable({
	    title: 'Archive Consump Queue List',
		    paging: true,
		    pageSize: 10,
		    sorting: true,
		    actions: {
		    listAction: function (postData, jtParams) {
		    console.log(postData);
			    return $.Deferred(function ($dfd) {
			    $.ajax({
			    url: '/mdrest/acq?page=' + jtParams.jtStartIndex + '&size='+jtParams.jtPageSize,
				    type: 'GET',
				    data: postData,
				    dataType: 'json',
				    success: function (data) {
				    $dfd.resolve(data);
				    },
				    error: function () {
				    $dfd.reject();
				    }
			    });
			    });
		    },
	    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
		    createAction:function (postData) {
		    console.log(postData);
			    return $.Deferred(function ($dfd) {
			    $.ajax({
			    url: '/mdrest/acq',
				    type: 'PUT',
				    data: postData,
				    dataType: 'json',
				    success: function (data) {
				    console.log(data);
					    $dfd.resolve(data);
				    },
				    error: function () {
				    $dfd.reject();
				    }
			    });
			    });
		    },
			    updateAction: function (postData) {
			    console.log(postData);
				    return $.Deferred(function ($dfd) {
				    $.ajax({
				    url: '/mdrest/acq',
					    type: 'POST',
					    data: postData,
					    dataType: 'json',
					    success: function (data) {
					    console.log(data);
						    $dfd.resolve(data);
					    },
					    error: function () {
					    $dfd.reject();
					    }
				    });
				    });
			    },
			    deleteAction: function (item) {
			    console.log(item);
				    return $.Deferred(function ($dfd) {
				    $.ajax({
				    url: '/mdrest/acq/' + item.queueId,
					    type: 'DELETE',
					    data: item,
					    dataType: 'json',
					    success: function (data) {
					    $dfd.resolve(data);
					    },
					    error: function () {
					    $dfd.reject();
					    }
				    });
				    });
			    }</security:authorize>
		    },
		    fields: {
		    queueId: {
		    key : true,
			    list: false,
			    create:false,
			    edit: false
		    },
			    sourceBatchId: {
			    title: 'Source Batch ID'
			    },
			    targetBatchId: {
			    title: 'Target Batch ID'

			    },
			    sourceProcessId: {
			    title: ' Source Process ID',
			    },
			    tableInsertTS: {
			    title: 'Insert Time',
				    create:true,
				    edit: true

			    },
			    tableStartTS: {
			    title: 'Start Time',
				    create:true,
				    edit: true
			    },
			    tableEndTS: {
			    title: 'End Time',
				    create:true,
				    edit: true

			    },
			    batchState: {
			    title: 'Batch State',
				    type:'Integer',
				    type: 'combobox',
				    options: '/mdrest/batchstatus/options',
				    defaultValue: "0"
			    },
			    batchMarking: {
			    title: 'Batch Marking'
			    },
			    processId: {
			    title: 'Process ID '
			    }
		    }
	    });
		    $('#Container').jtable('load');
	    });
	</script>
    </head>
    <body>

    <section style="width:100%;text-align:center;">
	<div id="Container"></div>
    </section>


</body>
</html>