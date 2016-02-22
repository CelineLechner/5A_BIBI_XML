<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version='1.0'
xmlns:xsl='http://www.w3.org/1999/XSL/Transform'>
<xsl:template match='/'>
<html>
	<body>
			<xsl:for-each select='root/images/image/block'>
				
				<xsl:for-each select='paragraph'>
				<p>
					<xsl:for-each select="character">
						<xsl:choose>
							<xsl:when test="@attr > 0">
								<span style="color:red"><xsl:value-of select='@value'/></span>
							</xsl:when>
							<xsl:otherwise>
								<span><xsl:value-of select='@value'/></span>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:for-each>	
				</p>
				</xsl:for-each>
			</xsl:for-each>
	</body>
</html>
</xsl:template>
</xsl:stylesheet>
