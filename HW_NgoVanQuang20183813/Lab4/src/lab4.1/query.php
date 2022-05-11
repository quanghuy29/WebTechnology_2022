<html>
    <head>
        <title>Table Output</title>
        <style>
            table, th, td {
                border:1px solid black;
            }
        </style>
    </head>
    
    <body>
        <?php
            $server = 'localhost';
            $user   = 'lampdb';
            $pass   = 'Anhemta123';
            $mydb   = 'mydatabase';
            $table_name = 'Products';

            // variables were geted form resquest
            // reference from stackoverflow: https://stackoverflow.com/questions/18023440/php-mysql-insert-into-with-get-method
            

            $link = mysqli_connect($server, $user, $pass, $mydb);
 
            // Check connection
            if($link === false){
                die("ERROR: Could not connect. " . mysqli_connect_error());
            }
 
            // Attempt create table query execution
            $sql = "SELECT * from Products";
            
            $getAllProducts = mysqli_query($link, $sql);
            if($getAllProducts){
                console.log("The Query is SELECT * FORM $table_name is successfull");
                
                
            } else{
                die ("Query Failed SQLcmd=$sql");
            }
 
            // Close connection
            mysqli_close($link);
        ?>
        <h2 style="color: blue;">Product Data</h2> 
        <p>The Query is SELECT * FORM <?php echo $table_name ?></p>
            <table style="width:50%">
                <tr>
                    <th>Num</th>
                    <th>Product</th>
                    <th>Cost</th>
                    <th>Weight</th>
                    <th>Count</th>
                </tr>
        <?php foreach($getAllProducts as $product): ?>
                <tr>
                    <td><span><?php echo $product['ProductID'] ?></span></td>
                    <td><span><?php echo $product['Product_desc'] ?></span></td>
                    <td><span><?php echo $product['Cost'] ?></span></td>
                    <td><span><?php echo $product['Weight'] ?></span></td>
                    <td><span><?php echo $product['Numb'] ?></span></td>
                </tr>
        <?php endforeach; ?>
            </table>
    </body>
</html>