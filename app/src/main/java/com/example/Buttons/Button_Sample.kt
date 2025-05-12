
@Composable
fun AllPropertiesButtonExample() {
    // Maintain state for enabling/disabling the button
    var isButtonEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Primary Button with all properties
        Button(
            onClick = {
                // Action on click
                isButtonEnabled = !isButtonEnabled
            },
            enabled = isButtonEnabled,
            modifier = Modifier
                .height(60.dp)
                .width(240.dp)
                .border(2.dp, Color.Gray, shape = RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isButtonEnabled) Color(0xFF6200EE) else Color.Gray,
                contentColor = Color.White,
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.DarkGray
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 8.dp,
                pressedElevation = 12.dp,
                disabledElevation = 0.dp
            ),
            interactionSource = remember { MutableInteractionSource() },
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (isButtonEnabled) "Click to Disable" else "Click to Enable",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Display a TextButton
        TextButton(
            onClick = { /* Secondary action */ }
        ) {
            Text("Text Button", color = Color.Blue)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display an OutlinedButton
        OutlinedButton(
            onClick = { /* Outline style action */ },
            border = BorderStroke(1.dp, Color.Red),
            shape = RoundedCornerShape(50)
        ) {
            Text("Outlined Button")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // IconButton (icon-only)
        IconButton(onClick = { /* Icon action */ }) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = Color.DarkGray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Floating Action Button (FAB)
        FloatingActionButton(
            onClick = { /* FAB action */ },
            containerColor = Color(0xFF03DAC5),
            shape = CircleShape
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }
    }
}
