<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/accountsForm.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
        <img class="topright" src="assets/home.png" onclick="toHomePage()">
    <section>
        <div class="main-form-container">
            <div id="form_section" class="form-container">
                <div class="accounts-form form-wraper ">
                    <div>
                        <div class="form-title">
                            <h2 id="accountstxt">Your accounts</h2>
                        </div>
                        <div class="abc">

                                <div class="action-button" style="display: inline-block">
                                    <form action="Savings" method="get">
                                        <button onclick="setSavings()">Savings account</button>
                                    </form>
                                </div>

                            <div class="action-button" style="display: inline-block">
                                <form action="FixedDeposit" method="post">
                                    <button onclick="redirectToFd()"> Fixed deposit account</button>
                                </form>
                            </div>

                            <div class="action-button" style="display: inline-block">
                                <form action="Loans" method="get">
                                    <button onclick="setLoan()">Loan account</button>
                                </form>
                            </div>
                            <div class="action-button" style="display: inline-block">
                                <button onclick="makeTransaction()">Make Transaction</button>
                            </div>
                        </div>


                    </div>
                </div>

            </div>

        </div>
    </section>
    <script>
        function redirectToFd() {
            sessionStorage.setItem("pressedTab", "fixedDeposit");
            // window.location.href = "fixedDeposit.jsp";
        }
        function setLoan(){
            sessionStorage.setItem("pressedTab", "loan");
        }
        function setSavings(){
            sessionStorage.setItem("pressedTab", "savings");
        }
        function makeTransaction() {
        var svType = "savings";
            $.ajax({
                url: "Accounts",
                type: "GET",
                data: {
                    svType: svType
                },
                success: function(response) {
                    console.log(response.status+ " : []]]]]]]]]]]]]]]]]]")
                    if (response.status === "fail"){
                        console.log("fail")
                        showAlert("First you have to create a Savings Account...");
                    }
                    if (response.status === "success"){
                        window.location.href = "transaction.jsp";
                    }
                },
                error: function() {
                    showAlert("An error occurred while processing the request.");
                }
            });
        }
        function showAlert(message) {
            alert(message);
        }
        function toHomePage() {
            window.location.href = "home.jsp";
        }
    </script>
</body>
</html>
