/**
* Horizontal Divider:-
**/

        @Composable
        fun HorizontalDividerExample() {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text("First item in list")
                HorizontalDivider(thickness = 2.dp)
                Text("Second item in list")
            }
        }  


/**
* Vertical Divider
**/
        @Composable
        fun VerticalDividerExample() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text("First item in row")
                VerticalDivider(color = MaterialTheme.colorScheme.secondary)
                Text("Second item in row")
            }
        }


/**
* 🎨 Customizing Divider
**/
@Composable
fun CustomDivider() {
    Divider(
        color = Color.Gray,
        thickness = 2.dp,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}
/**
* 🔹 Properties Explained:
*  color: Line color
*  thickness: Height of the line
*  modifier: Padding, width, etc.
**/


/**
* 🧪 Real-World Example
**/
@Composable
fun ProfileCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Followers")
        VerticalDivider(height = 20.dp)
        Text(text = "Following")
    }
}


/**
* 🛠️ Use With LazyColumn
**/
LazyColumn {
    items(itemsList) { item ->
        Text(text = item)
        Divider(color = Color.LightGray, thickness = 0.5.dp)
    }
}


