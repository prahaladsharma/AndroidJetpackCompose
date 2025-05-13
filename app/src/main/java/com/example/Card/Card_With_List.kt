/**
* ✅ Custom Card Component for Jetpack Compose
**/
@Composable
fun CustomCardItem(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    imageUrl: String = "",
    onClick: () -> Unit,
    onLongClick: () -> Unit = {},
    cornerRadius: Dp = 12.dp,
    elevation: Dp = 6.dp,
    borderColor: Color = Color.LightGray
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            ),
        shape = RoundedCornerShape(cornerRadius),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        border = BorderStroke(1.dp, borderColor),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (imageUrl.isNotBlank()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = title,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
            }

            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}

/**
* 🧪 Usage Example (LazyColumn Pagination UI)
**/



/**
* 📦 Dependencies
**/
// implementation("io.coil-kt:coil-compose:2.4.0") // for AsyncImage
@Composable
fun PaginatedCardList(items: List<MyItem>) {
    LazyColumn {
        items(items) { item ->
            CustomCardItem(
                title = item.title,
                subtitle = item.description,
                imageUrl = item.imageUrl,
                onClick = { /* handle click */ },
                onLongClick = { /* handle long press */ }
            )
        }
    }
}


