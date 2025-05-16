/**
* 🧩 1. Basic Concepts
**/

/**
* ➤ LazyColumn (List)
* Used for vertical list of items. 
* It only composes items that are visible on the screen — hence "lazy."
**/
LazyColumn {
    items(100) { index ->
        Text(text = "Item #$index")
    }
}


/**
* ➤ LazyRow (Horizontal List)
**/
LazyRow {
    items(10) { index ->
        Text(text = "Item #$index")
    }
}


/**
* ➤ LazyVerticalGrid (Grid Layout)
* Use for grid display.
**/
LazyVerticalGrid(columns = GridCells.Fixed(2)) {
    items(20) { index ->
        Text(text = "Grid Item #$index")
    }
}


/**
* List with State & Click Listeners
**/
@Composable
fun ItemList(viewModel: MyViewModel = viewModel()) {
    val items = viewModel.items.collectAsState()

    LazyColumn {
        items(items.value) { item ->
            Text(
                text = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.onItemClicked(item) }
            )
        }
    }
}

/**
* 💻 7. Grid with Card UI Example
**/
@Composable
fun GridScreen() {
    val items = (1..10).map { "Item $it" }

    LazyVerticalGrid(columns = GridCells.Adaptive(100.dp)) {
        items(items.size) { index ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(100.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(items[index])
                }
            }
        }
    }
}



