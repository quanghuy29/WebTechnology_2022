<html>
    <head>
        <title>Inserts Result</title>
    </head>

    <body>
        <?php
        $server = '127.0.0.1';
        $user = 'root';
        $pass = '';
        $mydb = 'lab4';
        $table_name = 'Products';
        $item_desc = $_GET["item_desc"];
        $weight = $_GET["weight"];
        $cost = $_GET["cost"];
        $num = $_GET["num"];

        $connect = mysqli_connect($server, $user, $pass, $mydb);
        if (!$connect) {
            die("Cannot connect to $server using $user");
        } else {
            $SQLInsert = "INSERT INTO $table_name VALUES (NULL, '$item_desc', $cost, $weight, $num)";
            mysqli_select_db($connect, $mydb);
            if (mysqli_query($connect, $SQLInsert)) {
                print "The Query is $SQLInsert";
                print "<br>Insert into $mydb was successful!";
            } else {
                die("Table Creation Failed Sqlcmd = $SQLInsert");
            }
            mysqli_close($connect);
        }
        ?>
    </body>
</html>