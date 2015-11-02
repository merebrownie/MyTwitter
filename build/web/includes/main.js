/* 
 All your JS code must be here.
 */

function changeColor() {
    alert("looking..");
    var tweet;
    var tweets = document.getElementsByClassName("tweet");
    alert(tweets.length);
    for(i = 0; i < tweets.length; i++) {
        tweet = tweets.item(i);
        alert(tweet);
        var startMention = tweets.indexOf("@") + 1;
        var endMention = tweets.indexOf(" ", startMention);
        alert(startMention + "" + endMention);
        var textToChange = tweet.substring(startMention, endMention);
        textToChange.style.color = "blue";
        alert(textToChange);
    }
    
}

function checkCookie() {
    if(document.cookie.indexOf("emailAddress=") > 0) {
        window.location.replace("login.jsp");
    }
}

function checkPassword() {
    alert("Checking password.");
    //if(document.cookie.test(document.login.password.value) || password == 
            //document.login.emailAddress.value) {
    if(document.cookie.indexOf(document.login.email.value) > 0) {
        alert(document.cookie + "" + document.login.email.value);
        if(document.cookie.indexOf(document.login.password.value) > 0) {
            alert(document.cookie + "" + document.login.password.value);
            alert("Correct password. Logging in...");
            window.location.replace("home.jsp");
            return true;
        } else {
            alert("No user found. Username/Password is incorrect.");
            alert(document.cookie + "" + document.login.password.value);
            return false;
        }
    }
    alert("Password checked.");
}

function validateForm() {
    var message = "";
    var emailAddress = document.signup.emailAddress.value;
    var password = document.signup.password.value;
    var birthmonth = document.signup.birthmonth.value;
    var birthdate = document.signup.birthdate.value;
    var birthyear = document.signup.birthyear.value;
    if(password.length < 7) {
        //alert("Password must be 7 characters long.");
        //return false;
        message += "Password must be at least 7 characters long. ";
    }
    if(birthmonth == "2") {
            if(birthdate == "29" || birthdate == "30") {
                if(birthdate == "29") {
                    if(birthyear == "1980" || birthyear == "1984" || 
                        birthyear == "1988" || birthyear == "1992" || 
                        birthyear == "1996" || birthyear == "2000" || 
                        birthyear == "2004" || birthyear == "2008" || 
                        birthyear == "2012" || birthyear == "2016") {
                        message += "Invalid birthdate. ";
                } else {
                        message += "Invalid birthdate. ";
                    }
                }
            }
        }
            
        if(birthdate == "31") {
            if(birthmonth == "2" || birthmonth == "4" || birthmonth == "6" 
                    || birthmonth == "9" || birthmonth == "11") {
                message += "Invalid birthdate. ";
            }
        }
        
        var re = /\S+@\S+\.\S+/;
        if(re.test(emailAddress) == false) {
            message += "Invalid email address. ";
        }
    
    if(message == "") {
        window.location.replace("home.jsp");
        return true;
    }
    else {
        alert(message);
        return false;
    }
}