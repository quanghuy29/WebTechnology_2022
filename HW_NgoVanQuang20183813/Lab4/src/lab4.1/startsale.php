<html>
    <head>
        <title>Table Output</title>
        <style>
            table, th, td {
                border:1px solid black;
            }
         
            .form_line {
                padding: 5px;
                width: 40%;
                display: flex;
            }
            .form_line__left {
                width: 50%;
            }
            .form_line__right {
                width: 50%;
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
        <h2 style="color: blue;">Select Product We Just Sold:</h2> 
        <form action="/sale.php" method="get">
            <div>
                <?php foreach($getAllProducts as $product): ?>
                    <input type="radio" id="<?php echo $product['ProductID'] ?>" name="item" value="<?php echo $product['Product_desc'] ?>">
                    <label for="<?php echo $product['Product_desc'] ?>"><?php echo $product['Product_desc'] ?></label>
                <?php endforeach; ?>
            </div>
            <div class="form_line">
                <div class="form_line__left">
                    <input type="submit" name="submit_btn" id="submit_btn" value="Click To Submit">
                </div>

                <div class="form_line__right">
                    <input type="button" src="/startsale.php" name="reset_btn" id="reset_btn" value="Reset">
                </div>
            </div>
        </form>

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