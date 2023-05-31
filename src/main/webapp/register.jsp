<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Finance Management System - Registration</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <link rel="stylesheet" href="css/registerForm.css">
</head>
<body>
    <section>
      <div class="main-form-container">
        <div id="form_section" class="form-container">
          <div class="Register-form form-wraper ">
            <div>
              <div class="form-title">
                <h2>Register</h2>
              </div>

              <div class="input-group">
                <div class="box">
                  <span>
                    <input id="un" placeholder="User Name" class="myInput" type="text"  name="userName"/>
                  </span>
                </div>
              </div>
              <div class="input-group">
                <div class="box">
                  <span>
                    <input id="eml" placeholder="Email" class="myInput" type="text" name="email" />
                  </span>
                </div>
              </div>
              <div class="input-group">
                <div class="box">
                  <span>
                    <input id="password" placeholder="Password" class="myInput" type="password" name="password" />
                  </span>
                </div>
              </div>
              <div class="input-group">
                <div class="box">
                  <span>
                    <input id="confirmPassword" placeholder="Confirm password" class="myInput" type="password" name="confpassword" />
                  </span>
                </div>
              </div>
              <div class="action-button">
                <button id="registerBtn" onclick="validateForm()">Register</button>
              </div>
              <div class="action-button">
                <button onclick="redirectToPage()">Back to Login</button>
              </div>

            </div>
          </div>

        </div>

      </div>
    </section>

    <script>
      function redirectToPage() {
        window.location.href = "index.jsp";
      }

      function validateForm() {
        var un = document.getElementById("un").value;
        var eml = document.getElementById("eml").value;
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirmPassword").value;

        if (un === '' || eml === '' || password==='' || confirmPassword ==='') {
          alert("Please fill all the fields");
          document.getElementById("un").focus();
        }else {
          if (!validateEmail(eml)) {
            document.getElementById("eml").value = "";
            document.getElementById("eml").focus();
            alert("Please enter a valid email address.");
            return false;
          }else {
            if (password !== confirmPassword) {
              alert("Password and Confirm Password do not match.");
              document.getElementById("password").value = "";
              document.getElementById("confirmPassword").value = "";

              // Focus on the password field
              document.getElementById("password").focus();
            }else {
              // alert("Registered Successfully...");
              // document.getElementById("myForm").submit();

              $.ajax({
                url: "User",
                type: "POST",
                data: {
                  userName: un,
                  email: eml,
                  password: password
                },
                success: function(response) {
                  if (response.result === "empty"){
                    alert("Please provide all details...");
                  }
                  if (response.result === "fail"){
                    alert("Registered unSuccessful...");
                  }
                  if (response.result === "success"){
                    alert("Registered Successfully...");
                    window.location.href = "index.jsp";
                  }
                },
                error: function() {
                  showAlert("An error occurred while processing the request.");
                }
              });
            }
          }
        }
      }
      function validateEmail(email) {
        var emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        return emailRegex.test(email);
      }

    </script>
</body>
</html>
