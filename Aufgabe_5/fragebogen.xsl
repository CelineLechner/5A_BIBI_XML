<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version='1.0'
xmlns:xsl='http://www.w3.org/1999/XSL/Transform'>
<xsl:template match='/'>
<html>
	<body>
        
        <input id="submit" name="submit" type="submit" value="Abschicken"></input><br/>
        
		<span style="color:green; font-size:50px"><xsl:value-of select='questionnaire/header/label'/></span>
        

	<xsl:for-each select='//.'>

		<xsl:if test="name()='closedEndedQ'">
			<h2><xsl:value-of select='@disp_id'/>. 
				<xsl:value-of select='text'/></h2>
			<p><xsl:value-of select='qdescription'/></p>

			<xsl:for-each select='choices'>
				<xsl:choose>
					<xsl:when test="@mult='true'">
						<xsl:for-each select='choice'>
							<input type='checkbox'/>
							<xsl:value-of select='text'/>
							<br/>
						</xsl:for-each>
					</xsl:when>

					<xsl:when test="@mult='false'">
						<xsl:for-each select='choice'>
							<input type='radio' name='aa'/>
							<xsl:value-of select='text' />
							<br/>
						</xsl:for-each>
					</xsl:when>
				</xsl:choose>
				<br/>
			</xsl:for-each>
		</xsl:if>




		<xsl:if test="name()='openendedMatrixQ'">
			<br/>
			<h2><xsl:value-of select='@disp_id'/>. 
				<xsl:value-of select='text'/></h2>
			<p><xsl:value-of select='qdescription'/></p>

			<table border="1">
				<xsl:for-each select='questions/question'>
					<tr>
						<td><xsl:value-of select='text'/></td>
						<td><input type='textbox'/></td>
					</tr>
				</xsl:for-each>
			</table>
		</xsl:if>






		<xsl:if test="name()='questionMatrixMult'">
			
			<h2><xsl:value-of select='@disp_id'/>.  
				<xsl:value-of select='text'/></h2>
            <p><xsl:value-of select='qdescription'/></p>

			<table border="1">
			<tr>
				<th></th>
				<xsl:for-each select='choices/choice'>
					<th>
						<xsl:value-of select='text'/>
					</th>
				</xsl:for-each>
			</tr>

			<xsl:for-each select='questions/question'>
				<xsl:variable name="id">
					<xsl:value-of select="@id"/>
				</xsl:variable>

				<tr>
					<td><xsl:value-of select='text'/></td>

					<td><input type='radio' name='{$id}'/></td>
					<td><input type='radio' name='{$id}'/></td>
					<td><input type='radio' name='{$id}'/></td>
					<td><input type='radio' name='{$id}'/></td>
					<td><input type='radio' name='{$id}'/></td>
				</tr>
			</xsl:for-each>
			</table>
		</xsl:if>
        

	</xsl:for-each>
		
	</body>
</html>
</xsl:template>
</xsl:stylesheet>

