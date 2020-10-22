package model;



public class Payment {
	
	
	
    private String cardName;
    private String cardNumber;
    private String exp;
    private String cvv;
    private String appleID;
    private String applePassword;
    

        
 public Payment(String cardName, String cardNumber, String exp, String cvv, String appleID, String applePassword){
	 
	 this.cardName = cardName;
	 this.cardNumber = cardNumber;
	 this.exp = exp;
	 this.cvv = cvv;
	 this.appleID =appleID;
	 this.applePassword = applePassword;

        }
  
 public String getCardName() {
     return cardName;
 }

 public String getCardNumber() {
     return cardNumber;
 }

 public String getExp() {
     return exp;
 }

 public String getCvv() {
     return cvv;
 }

 public String getAppleID() {
     return appleID;
 }

 public String getApplePassword() {
     return applePassword;
 }


}

 

    