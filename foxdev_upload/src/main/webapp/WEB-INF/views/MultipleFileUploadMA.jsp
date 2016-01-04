<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<script>
 
    function AddMoreFile(tableID) {
        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        var col1 = row.insertCell(0);
        var colInput = document.createElement("input");
        colInput.type = "file";
        colInput.name="files["+(rowCount)+"]";
        col1.appendChild(colInput);
    }
 
     
    
</script> 
 
<h1>Spring Multiple File Upload Example @ModelAttribute</h1>
 
<form:form method="post" action="uploadMultipleFilesMA.do"  modelAttribute="multipleFileUploadForm"   enctype="multipart/form-data">
 
    <p>Select files to upload. Click on Add More button to add more files.</p>

	<TABLE id="fuTable" border="1">
		<TR>
			<TD><input name="files[0]" type="file"></TD>
		</TR>

	</TABLE>
	<br>    <input  type="button" value="Add More File"  onclick="AddMoreFile('fuTable')">
    <input type="submit" value="Upload">
 
</form:form>