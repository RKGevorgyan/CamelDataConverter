<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output indent="yes" method="xml" omit-xml-declaration="yes"/>
    <xsl:param name="modelVersion"/>
    <xsl:param name="modelName"/>

    <xsl:template match="/">
        <xsl:text>{"data":</xsl:text>
        <xsl:call-template name="model">
            <xsl:with-param name="model" select="node()"/>
        </xsl:call-template>
        <xsl:text>,"modelName":"</xsl:text>
        <xsl:value-of select="$modelName"/>
        <xsl:text>","modelVersion":"</xsl:text>
        <xsl:value-of select="$modelVersion"/>
        <xsl:text>"}</xsl:text>
    </xsl:template>

    <xsl:template name="model">
        <xsl:param name="model"/>
        <xsl:text>{</xsl:text>
        <xsl:text>"birth_date":"</xsl:text>
        <xsl:value-of select="$model/Party/SrcBirthDate"/><xsl:text>",</xsl:text>
        <xsl:text>"first_name":"</xsl:text>
        <xsl:value-of select="$model/Party/SrcFirstName"/><xsl:text>",</xsl:text>
        <xsl:text>"middle_name":"</xsl:text>
        <xsl:value-of select="$model/Party/SrcMiddleName"/><xsl:text>",</xsl:text>
        <xsl:text>"last_name":"</xsl:text>
        <xsl:value-of select="$model/Party/SrcLastName"/><xsl:text>",</xsl:text>
        <xsl:text>"party_type":"</xsl:text>
        <xsl:value-of select="$model/Party/SrcPartyType/RecordCode"/><xsl:text>",</xsl:text>
        <xsl:if test="$model/ContactList/Contact">
            <xsl:call-template name="contact">
                <xsl:with-param name="contact" select="$model/ContactList"/>
            </xsl:call-template>
        </xsl:if>
        <xsl:text>"sysAttr":{</xsl:text>
        <xsl:text>"source_update_time":"</xsl:text>
        <xsl:value-of select="$model/Party/SourceTimestamp"/><xsl:text>",</xsl:text>
        <xsl:text>"source_id":"</xsl:text>
        <xsl:value-of select="$model/Party/SourceId/ObjectId"/><xsl:text>",</xsl:text>
        <xsl:text>"source_system":"</xsl:text>
        <xsl:value-of select="$model/Party/SourceId/ObjectId"/><xsl:text>"</xsl:text>
        <xsl:text>}</xsl:text>
        <xsl:text>}</xsl:text>
    </xsl:template>

    <xsl:template name="contact">
        <xsl:param name="contact"/>
        <xsl:text>"contacts":[</xsl:text>

        <xsl:text>],</xsl:text>
    </xsl:template>

</xsl:stylesheet>