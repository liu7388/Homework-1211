# Homework-1211

This project focuses on modernizing asynchronous task handling by adopting Kotlin Coroutines and optimizing UI interactions and list performance in Android Labs (Chapters 7-9).

## Key Updates

* **[Lab 7](https://github.com/liu7388/Homework-1211/tree/main/Lab7): GridView Interaction (AlertDialog)** \
    Enhanced the `GridView` item click listener by implementing an `AlertDialog` for purchase confirmation. This ensures users verify their choice before processing, improving the user experience and preventing accidental interactions.

* **[Lab 8](https://github.com/liu7388/Homework-1211/tree/main/Lab8) : RecyclerView Optimization (Item Specific Updates)** \
    Replaced the inefficient `notifyDataSetChanged()` with granular update methods (e.g., `notifyItemInserted`, `notifyItemRemoved`). This significantly improves rendering performance by only refreshing affected items and enables default entry/exit animations.
  
* **[Lab 9_1](https://github.com/liu7388/Homework-1211/tree/main/Lab9_1) & [Lab 9_2](https://github.com/liu7388/Homework-1211/tree/main/Lab9_2): Asynchronous Migration (Kotlin Coroutines)** \
    Refactored asynchronous operations by migrating from legacy `Thread` and `Handler` patterns to Kotlin Coroutines. This simplifies concurrency management, eliminates callback nesting, and ensures thread safety when updating the UI from background tasks.
