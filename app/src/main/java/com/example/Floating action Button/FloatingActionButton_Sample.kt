/**
* 📌 Basic Syntax
**/
FloatingActionButton(
    onClick = { /* Handle FAB click */ }
) {
    Icon(Icons.Default.Add, contentDescription = "Add")
}


/**
* ✨ Types of FAB
**/
/**
* 1. Regular FAB
**/
FloatingActionButton(
    onClick = { /* click action */ },
    containerColor = MaterialTheme.colorScheme.primary
) {
    Icon(Icons.Filled.Add, contentDescription = "Add")
}


/**
* 2. Extended FAB (Text + Icon)
**/
ExtendedFloatingActionButton(
    text = { Text("Add Item") },
    icon = { Icon(Icons.Filled.Add, contentDescription = "Add") },
    onClick = { /* Action */ }
)


/**
* 3. Small / Large FAB (Material3)
**/
SmallFloatingActionButton(onClick = { /* ... */ }) {
    Icon(Icons.Filled.Edit, contentDescription = "Edit")
}

LargeFloatingActionButton(onClick = { /* ... */ }) {
    Icon(Icons.Filled.Share, contentDescription = "Share")
}


/**
* 🧱 Example with Scaffold
**/
@Composable
fun FabExampleScreen() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Action */ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {
        // Screen content here
        Text("Welcome to FAB Example!", modifier = Modifier.padding(16.dp))
    }
}


/**
* 🎨 Styling and Customization
**/
FloatingActionButton(
    onClick = { /* ... */ },
    containerColor = Color.Magenta,
    shape = RoundedCornerShape(16.dp),
    elevation = FloatingActionButtonDefaults.elevation(8.dp)
) {
    Icon(Icons.Filled.Email, contentDescription = "Email")
}



