<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Currency Exchange Calculator</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery for handling the form submission -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        /* เพิ่มพื้นหลัง GIF */
        body {
            background: url('https://cdn.dribbble.com/users/2287419/screenshots/18099642/media/58cd4bf0e4d9a7e3bf5f2aeb527ac0e3.gif') no-repeat center center fixed;
            background-size: cover; /* ขยายให้เต็มหน้าจอ */
            color: #FF6F00; /* สีข้อความ */
        }

        .card {
            background-color: rgba(31, 31, 31, 0.9); /* ทำให้การ์ดโปร่งแสง */
            border: none;
        }

        .card-header, .card-body {
            border-color: #333; /* สีเส้นขอบ */
        }

        .btn-primary {
            background-color: #034670; /* สีปุ่ม */
            border-color: #034670;
        }

        .btn-primary:hover {
            background-color: #290d6b;
            border-color: #290d6b;
        }
        .btn-info {
            color: #fff;
            background-color: #434343; /* สีปุ่ม */
            border-color: #d70a0a;
        }

        .btn-info:hover {
            color: #fff;
            background-color: #d70a0a;
            border-color: #434343;
        }

        .form-select, .form-control {
            background-color: #4b0000; /* พื้นหลัง input */
            color: #00FF00; /* สีข้อความ input */
            border: 1px solid #444;
        }

        .form-select:focus, .form-control:focus {
            background-color: #444;
            border-color: #6200ea;
            box-shadow: 0 0 0 0.2rem rgba(98, 0, 234, 0.25);
        }
    </style>
</head>
<body 
    class="d-flex justify-content-center align-items-center min-vh-100">
    <div class="container">
        <h1 <cpe3243:CPE6651630177 /></h1>
        <div class="row justify-content-center">
            <!-- Currency Calculator -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header text-light">
                        <h5>Currency Converter</h5>
                    </div>
                    <div class="card-body">
                        <form id="convertForm">
                            <div class="mb-3">
                                <label for="fromCurrency" class="form-label">From Currency</label>
                                <select id="fromCurrency" class="form-select">
                                    <!-- Predefined options -->
                                    <option value="USD">USD</option>
                                    <option value="EUR">EUR</option>
                                    <option value="GBP">GBP</option>
                                    <option value="JPY">JPY</option>
                                    <option value="CAD">CAD</option>
                                    <option value="AUD">AUD</option>
                                    <option value="CNY">CNY</option>
                                    <option value="THB">THB</option>
                                    <option value="INR">INR</option>
                                    <option value="MXN">MXN</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="toCurrency" class="form-label">To Currency</label>
                                <select id="toCurrency" class="form-select">
                                    <!-- Predefined options -->
                                    <option value="USD">USD</option>
                                    <option value="EUR">EUR</option>
                                    <option value="GBP">GBP</option>
                                    <option value="JPY">JPY</option>
                                    <option value="CAD">CAD</option>
                                    <option value="AUD">AUD</option>
                                    <option value="CNY">CNY</option>
                                    <option value="THB">THB</option>
                                    <option value="INR">INR</option>
                                    <option value="MXN">MXN</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="amount" class="form-label">Amount</label>
                                <input type="number" id="amount" class="form-control" placeholder="Enter amount">
                            </div>
                            <button type="submit" class="btn btn-primary w-100">Convert</button>
                        </form>
                        <div class="mt-3">
                            <h5 class="text-light">Converted Amount: <span id="convertedAmount"></span></h5>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Link to other pages for checking and comparing exchange rates -->
        <div class="text-center mt-4">
            <a href="latest.jsp" class="btn btn-info">Check Latest Exchange Rates</a>
            <br>
            <a href="compare.jsp" class="btn btn-info">Compare Exchange Rates</a>
        </div>
    </div>

    <!-- Bootstrap 5 JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        // Predefined exchange rates
        const exchangeRates = {
            USD: { EUR: 0.93, GBP: 0.83, JPY: 133.33, CAD: 1.36, AUD: 1.52, CNY: 7.06, THB: 34.5, INR: 82.75, MXN: 18.54 },
            EUR: { USD: 1.08, GBP: 0.89, JPY: 143.78, CAD: 1.46, AUD: 1.63, CNY: 7.59, THB: 37.1, INR: 89.01, MXN: 19.94 },
            GBP: { USD: 1.21, EUR: 1.12, JPY: 161.55, CAD: 1.64, AUD: 1.83, CNY: 8.51, THB: 41.7, INR: 99.92, MXN: 22.43 },
            JPY: { USD: 0.0075, EUR: 0.0069, GBP: 0.0062, CAD: 0.012, AUD: 0.011, CNY: 0.058, THB: 0.26, INR: 0.62, MXN: 0.14 },
            CAD: { USD: 0.74, EUR: 0.69, GBP: 0.61, JPY: 83.33, AUD: 0.89, CNY: 5.27, THB: 25.41, INR: 60.64, MXN: 14.2 },
            AUD: { USD: 0.66, EUR: 0.61, GBP: 0.55, JPY: 74.45, CAD: 0.89, CNY: 4.72, THB: 22.7, INR: 54.3, MXN: 12.7 },
            CNY: { USD: 0.14, EUR: 0.13, GBP: 0.12, JPY: 17.3, CAD: 0.19, AUD: 0.21, THB: 4.8, INR: 12.85, MXN: 2.41 },
            THB: { USD: 0.029, EUR: 0.027, GBP: 0.024, JPY: 3.85, CAD: 0.039, AUD: 0.044, CNY: 0.21, INR: 2.61, MXN: 0.51 },
            INR: { USD: 0.012, EUR: 0.011, GBP: 0.010, JPY: 1.62, CAD: 0.016, AUD: 0.018, CNY: 0.078, THB: 0.38, MXN: 0.19 },
            MXN: { USD: 0.054, EUR: 0.050, GBP: 0.045, JPY: 7.14, CAD: 0.070, AUD: 0.079, CNY: 0.42, THB: 1.97, INR: 5.17 }
        };

        // Handle Currency Conversion
        $('#convertForm').on('submit', function(event) {
            event.preventDefault();

            const fromCurrency = $('#fromCurrency').val();
            const toCurrency = $('#toCurrency').val();
            const amount = $('#amount').val();

            if (!amount || amount <= 0) {
                alert('Please enter a valid amount.');
                return;
            }

            // Get the conversion rate from the predefined exchangeRates object
            const rate = exchangeRates[fromCurrency][toCurrency];
            const convertedAmount = (amount * rate).toFixed(2);

            $('#convertedAmount').text(`${convertedAmount} ${toCurrency}`);
        });
    </script>
</body>
</html>
