package com.dtxx.platform;

import java.util.Objects;

public class ServerEncodeTest {
    public static final String AESKey1 = "RVF6RZ4WyuiMcg9t";
    public static final String AESKey2 = "8bk0vuSSCKOIehiI";
    public static final String SPLIT = ",";

    public static String encodeData(String sSrc,String outPublicKey,String aesKey1,String aesKey2) throws Exception {
        String paramAes = AES.Encrypt(sSrc, aesKey1, aesKey2);
        String paramSha256 = SHA256.encrypt(sSrc);
        String data = paramSha256 + SPLIT + aesKey1 + SPLIT + aesKey2;

        return paramAes + SPLIT + new String(RSAEncrypt.encrypt(RSAEncrypt.loadPublicKeyReturn(outPublicKey), data.getBytes()));
    }

    public static String decode(String sSrc,String privateKey,String aesKey1K,String aesKey2K) throws Exception { 
        String[] data = sSrc.split(ServerEncodeTest.SPLIT);
        String paramAes = data[0];
        String paramRsa = data[1];

        String paramData = new String(RSAEncrypt.decrypt(RSAEncrypt.loadPrivateKeyReturn(privateKey), paramRsa.getBytes()));
       
        String sha256Data[] = paramData.split(ServerEncodeTest.SPLIT);
        //String paramSha256 = Base64Util.decode(sha256Data[0]);
        String aesKey1 = sha256Data[1];
        String aesKey2 = sha256Data[2]; 
        if(!aesKey1.equals(aesKey1K)||!aesKey2.equals(aesKey2K))
        	return null;
        

        String param = AES.Decrypt(paramAes, aesKey1, aesKey2);

        /*String paramReSha256 = Base64Util.decode(SHA256.encrypt(param));

        if (Objects.equals(paramSha256, paramReSha256)) {
            return param;
        }*/

        return param;
    }
    public static void main(String[] args) throws Exception {
//    	String s=encodeData("{\r\n" + 
//    			"    \"operation\": \"insert\",\r\n" + 
//    			"    \"sysCode\": \"FINA_SYS\",\r\n" + 
//    			"    \"contractId\": \"122\",\r\n" + 
//    			"    \"paymentNo\": \"FKSQ171116010\",\r\n" + 
//    			"    \"paymentName\": \"\",\r\n" + 
//    			"    \"paymentDate\": \"\",\r\n" + 
//    			"    \"paymentStatus\": \"Y\",\r\n" + 
//    			"    \"paiedWithTax\": \"\",\r\n" + 
//    			"    \"paiedNoTax\": \"\",\r\n" + 
//    			"    \"tax\": \"\",\r\n" + 
//    			"    \"paymentInfo\": \"\",\r\n" + 
//    			"    \"paymentType\": \"\",\r\n" + 
//    			"    \"paymentMethod\": \"\",\r\n" + 
//    			"    \"paymentSpec\": \"\",\r\n" + 
//    			"    \"termName\": \"预付款\",\r\n" + 
//    			"    \"termContent\": \"合同签订\",\r\n" + 
//    			"    \"hanlePersonId\": \"2942\",\r\n" + 
//    			"    \"hanleDeptId\": \"1000054407\",\r\n" + 
//    			"    \"applyDate\": \"\",\r\n" + 
//    			"    \"accountBank\": \"\",\r\n" + 
//    			"    \"accountName\": \"\",\r\n" + 
//    			"    \"bankAccount\": \"\",\r\n" + 
//    			"    \"custOrgCode\": \"\",\r\n" + 
//    			"    \"contractOrgId\": \"\",\r\n" + 
//    			"    \"vendorCode\": \"\",\r\n" + 
//    			"    \"arrivalBeginDate\": \"\",\r\n" + 
//    			"    \"arrivalEndDate\": \"\",\r\n" + 
//    			"    \"settleDate\": \"\",\r\n" + 
//    			"    \"weighNumber\": \"\",\r\n" + 
//    			"    \"settleNumber\": \"\",\r\n" + 
//    			"    \"settlePrice\": \"\",\r\n" + 
//    			"    \"checkHeatValue\": \"\",\r\n" + 
//    			"    \"settleHeatValue\": \"\",\r\n" + 
//    			"    \"factoryPrice\": \"\",\r\n" + 
//    			"    \"settleWithTax\": \"\",\r\n" + 
//    			"    \"settleTranFee\": \"\",\r\n" + 
//    			"    \"settleTotal\": \"\",\r\n" + 
//    			"    \"oweTon\": \"\",\r\n" + 
//    			"    \"oweCal\": \"\",\r\n" + 
//    			"    \"TranType\": \"\",\r\n" + 
//    			"    \"payAmount\": \"5120098.36\",\r\n" + 
//    			"    \"planAmount\": \"\",\r\n" + 
//    			"    \"needPayAmount\": \"\",\r\n" + 
//    			"    \"advancedPay\": \"\",\r\n" + 
//    			"    \"needPay\": \"\",\r\n" + 
//    			"    \"applyNeedPay\": \"\",\r\n" + 
//    			"    \"orglayer\": \"\",\r\n" + 
//    			"    \"appyNeedPayCode\": \"\",\r\n" + 
//    			"    \"payableAmount\": \"\",\r\n" + 
//    			"    \"promiseDate\": \"\",\r\n" + 
//    			"    \"budgetPlanDate\": \"\",\r\n" + 
//    			"    \"orgId\": \"\",\r\n" + 
//    			"    \"deptId\": \"1000054407\",\r\n" + 
//    			"    \"createUser\": \"2942\",\r\n" + 
//    			"    \"createTime\": \"2017-11-16 14:40:33\",\r\n" + 
//    			"    \"updateTime\": \"2017-11-16 14:40:33\",\r\n" + 
//    			"    \"updateUser\": \"2942\",\r\n" + 
//    			"    \"attachment\": {\r\n" + 
//    			"        \"operation\": \"\",\r\n" + 
//    			"        \"busiId\": \"\",\r\n" + 
//    			"        \"busiType\": \"\",\r\n" + 
//    			"        \"docType\": \"\",\r\n" + 
//    			"        \"docName\": \"\",\r\n" + 
//    			"        \"requiredFlag\": \"\",\r\n" + 
//    			"        \"uploadPersonId\": \"\",\r\n" + 
//    			"        \"uploadDate\": \"\",\r\n" + 
//    			"        \"typeID1\": \"\",\r\n" + 
//    			"        \"typeID2\": \"\",\r\n" + 
//    			"        \"typeID3\": \"\",\r\n" + 
//    			"        \"typeID4\": \"\",\r\n" + 
//    			"        \"typeID5\": \"\",\r\n" + 
//    			"        \"batchId\": \"\",\r\n" + 
//    			"        \"orderNo\": \"\",\r\n" + 
//    			"        \"url\": \"\"\r\n" + 
//    			"    },\r\n" + 
//    			"    \"auditHis\": {\r\n" + 
//    			"        \"operation\": \"\",\r\n" + 
//    			"        \"orderNo\": \"\",\r\n" + 
//    			"		\"busiId\":\"\",\r\n" + 
//    			"        \"nodeName\": \"\",\r\n" + 
//    			"        \"auditPersonId\": \"\",\r\n" + 
//    			"        \"receiveDate\": \"\",\r\n" + 
//    			"        \"auditDate\": \"\",\r\n" + 
//    			"        \"auditDeptId\": \"\",\r\n" + 
//    			"        \"auditOrgCode\": \"\",\r\n" + 
//    			"        \"auditResult\": \"\",\r\n" + 
//    			"        \"auditInfo\": \"\",\r\n" + 
//    			"        \"busiType\": \"\",\r\n" + 
//    			"        \"busiEntityId\": \"\",\r\n" + 
//    			"        \"activityId\": \"\",\r\n" + 
//    			"        \"itemKey\": \"\"\r\n" + 
//    			"    }\r\n" + 
//    			"}");
//    	String s=encodeData("{asdas:'1',sadas:'安出'}");
//		System.out.println(s);
//		System.out.println(decode(s));
    	String s=encodeData("FINA_SYS_JITUAN", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAAaS8uZGMYjactCWO6vgcpwUyMSAG7RBHOGLAmP1GKXwVZ86HYFD2JXLgiaUXNj4rMSGDQLpTqrSM0/QoBwZ2oEOUNaBF4wDyGZnRAk9w16kr9uvR0C++YOp2XquRzttWvakQszIgUhEYN/kTn5BfMSOxqjTUgNTuIkQjE0ansQIDAQAB\"", "RVF6RZ4WyuiMcg9t", "8bk0vuSSCKOIehiI");
    	String ss=decode("qoTZcV12J9e3wud/Gvb6JQ==,CdQNk6RkmyQ6I8g1pQEnEAT8lRsb728uNWZjiR7UijzFa4MJwonqggFjTN9kZJAinEs+6w8YYoGR\r\n" + 
    			"xT1QCEyu8gedtkmeR2fLCm5BQJjgg0nS81tHtzXqAsG1dLq/RsihbmbPcze2piUERM3ansTnoekO\r\n" + 
    			"rAzxj6bI2g27ZnsvKSQ=\r\n" + 
    			"", "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMABpLy5kYxiNpy0JY7q+BynBTIxIAbtEEc4YsCY/UYpfBVnzodgUPYlcuCJpRc2PisxIYNAulOqtIzT9CgHBnagQ5Q1oEXjAPIZmdECT3DXqSv269HQL75g6nZeq5HO21a9qRCzMiBSERg3+ROfkF8xI7GqNNSA1O4iRCMTRqexAgMBAAECgYEAqs3LeoepzrRf2K746lZkOTjrCmdhEkg5PuIOFz8BsDSfSXt64jrwSsetlclcr3V1gH7ITTZ7iopE6zJx9FmriLeCkmQC+ZRUGH1nvFWO3tQhCxez/yOmQKDDb5VtDgPyKI9DYCv1731MUy1tusbhzO/q3Djs3PFR6cDKSKPAMEECQQDzcez3CUHaHHN5Ip+GB8F9KkBtNtZNUJM6MWNXa/E7LyPxDvVoPNjqNsn2taFT+ErT9jd3RQuL65EW9IR2J4P9AkEAyeiZ/VmamWMKv5atsdZOOikRrzF3RV95hxAfIFhcOsvh20k5sxZ12V8tfWHU848j3aBdbfcwXacUa+EmunNOxQJAYfzqP6kXO36WMBzyjtVSWVoHYq5e5fHXwOWeMdL93y+jTOUTfGh5exSgiFAlZpIbNGP3gWdmNWSVGuNC6fgP1QJBAJXoPrydlG0h3c8Vfmy1ImuIJnsejOFS25Xt4E5RjFiTG2OGYHKY0HLNeabHxiX3NrfL4tJMXvqJnF3kUq7IAckCQQCBsE8ilzQkQInYbfFiGlRZGRr1uUAey2TArN605Xv+M42O/P3jhd1DUG24acogMM4qrHrBUIsvXAj5mRIh0r62", "RVF6RZ4WyuiMcg9t", "8bk0vuSSCKOIehiI");
System.out.println(s);	
System.out.println(ss);	
}
}
