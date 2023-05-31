<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="css/homeForm.css">
</head>
<body>
  <%--  <img src="img_5terre_wide.jpg" alt="Cinque Terre" width="1000" height="300">--%>
  <img class="topright" src="assets/signOut.png" onclick="logOut()">

  <section>
    <div class="main-form-container">
      <div id="form_section" class="form-container">
        <div class="Home-form form-wraper ">
          <div>
            <div class="form-title">
              <h2 id="welcometxt">Welcome to ABC Finance</h2>
            </div>
            <form action="Accounts" method="get">
              <div class="action-button">
                <button>Accounts</button>
              </div>
            </form>
              <div class="action-button">
                <button>Services</button>
            </div>
              <div class="action-button">
                <button>About us</button>
              </div>

          </div>
        </div>

      </div>

    </div>
  </section>
  <script>
    function logOut() {
      window.location.href = "index.jsp";
    }
  </script>
</body>
</html>
