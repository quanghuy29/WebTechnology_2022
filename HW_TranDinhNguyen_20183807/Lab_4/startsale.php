<html>
    <head>
        <title>Update product</title>
    </head>
    <body>
        <?php
        $server = '127.0.0.1';
        $user = 'root';
        $pass = '';
        $mydb = 'lab4';
        $table_name = 'Products';
        $connect = mysqli_connect($server, $user, $pass, $mydb);
        if (!$connect) {
            die("Cannot connect to $server using $user");
        } else {
            $SQLcmd = "SELECT * FROM $table_name";
            mysqli_select_db($connect, $mydb);
            if (mysqli_query($connect, $SQLcmd)) {
                $result = mysqli_query($connect, $SQLcmd);
            } else {
                die("Table Creation Failed Sqlcmd = $SQLcmd");
            }
            ?>
            <font size = "6" color="blue" >Select Product We Just Sold</font>
            <form action="sale.php" method="GET">
                <?php
                while ($row = mysqli_fetch_array($result)) {
                    $name = $row["Product_desc"];
                    print "<input type = 'radio' value =" . $row["Product_desc"] . " name = $name>" . $row["Product_desc"];
                }
                ?>
                <br>
                <input type="submit" value="Click to submit">
                <input type="reset" value="Reset">
            </form>
            <?php
            $result = mysqli_query($connect, $SQLcmd);
            print "<br>The Query is $SQLcmd<br>";
            print "<table border = 1> "
                    . "<tr>"
                    . "<td>Num</td>"
                    . "<td>Product</td>"
                    . "<td>Cost</td>"
                    . "<td>Weight</td>"
                    . "<td>Count</td>"
                    . "</tr>";
            while ($row = mysqli_fetch_array($result)) {
                print "<tr>"
                        . "<td>" . $row["ProductID"] . "</td>"
                        . "<td>" . $row["Product_desc"] . "</td>"
                        . "<td>" . $row["Cost"] . "</td>"
                        . "<td>" . $row["Weight"] . "</td>"
                        . "<td>" . $row["Numb"] . "</td>"
                        . "</tr>";
            }
            print "</table>";
            mysqli_close($connect);
        }
        ?>
    </body>
</html>