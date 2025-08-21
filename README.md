# 🎥 Cinema Ticket Ordering System

A **Cinema Ticket Ordering System** that supports multiple cinemas, manages movie shows, processes ticket bookings and cancellations, handles ticket price updates, and maintains cinema-wise revenue statistics. The system also supports safe handling of **concurrent ticket bookings** and cancellations.

---

## 🚀 Features

### **1. Cinema Integration**

* Add new cinemas and register movie shows.
* Each show includes:

  * 🎬 Movie Name
  * 📅 Screening Date & Time
  * 💰 Ticket Price
  * 🎟 Seating Capacity
* Generates a **unique show\_id** for each registered show.

### **2. Cinema Price Updates**

* Update ticket prices for existing shows.

### **3. Cinema Show Management**

* Start and end shows using their `show_id`.
* Prevent ending a show **before it starts**.

### **4. Ticket Ordering**

* Customers provide:

  * Movie Name
  * Screening Date & Time
  * Number of Tickets
* System automatically selects the **cheapest available cinema**.
* **Single order → Single show only**.
* Handles booking failures:

  * ❌ "Booking Unavailable" → Not enough seats.
  * ❌ "Show Already Started" → Cannot book after show start.

### **5. Ticket Cancellation & Refund Policy**

* Cancel bookings anytime.
* Refund Rules:

  * ✅ Before show starts → **50% refund**
  * ❌ After show starts → **No refund**

### **6. System Statistics**

* View cinema-wise statistics:

  * Total Revenue
* Bonus: More advanced stats per cinema can be added.

### **7. Concurrency Handling (Bonus)**

* Thread-safe handling for booking and cancellation.
* Synchronized blocks used for **critical sections**.

```python
<sync block start>
# Cinemas registering shows and capacities
<sync block end>

<async block start>
# Concurrent booking and cancellation requests
<async block end>
```

---

## 🛠️ Technical Guidelines

* Uses **in-memory data structures**.
* Modular and clean architecture.
* Proper **error handling**.
* Thread-safe concurrency support.

---

## 🧪 Test Cases

### **Test Case 1: Simple Registration and Booking**

```python
show_id = registerShow("Grand Cinema", "Avengers", "02/05/2025 10:00 AM", 15.0, 100)
order_ticket("Avengers", "02/05/2025 10:00 AM", 60)
start_show(show_id)
end_show(show_id)
print_system_stats()
```

**Output:**

```
Grand Cinema, Revenue: 900
```

---

### **Test Case 2: Ending Show Before Starting**

```python
show_id_1 = registerShow("Grand Cinema", "Avengers", "02/05/2025 10:00 AM", 15.0, 100)
show_id_2 = registerShow("PVR", "X-Men", "02/05/2025 11:00 AM", 15.0, 100)

order_ticket("Avengers", "02/05/2025 10:00 AM", 60)
end_show(show_id_1)   # ❌ Error: show has not started yet
start_show(show_id_1)
```

---

### **Test Case 3: Cheapest Show Selection**

```python
show_id_1 = registerShow("Grand Cinema", "Avengers", "02/05/2025 10:00 AM", 15.0, 100)
show_id_2 = registerShow("PVR", "Avengers", "02/05/2025 10:00 AM", 10.0, 100)

order_ticket("Avengers", "02/05/2025 10:00 AM", 60)
start_show(show_id_1)
end_show(show_id_1)
start_show(show_id_2)
end_show(show_id_2)
print_system_stats()
```

**Output:**

```
PVR, Revenue: 600
Grand Cinema, Revenue: 0
```

---

### **Test Case 4: Booking Unavailability**

```python
show_id_1 = registerShow("Grand Cinema", "Avengers", "02/05/2025 10:00 AM", 15.0, 100)
show_id_2 = registerShow("PVR", "Avengers", "02/05/2025 10:00 AM", 10.0, 100)

order_ticket("Avengers", "02/05/2025 10:00 AM", 60)  # Booked at PVR
order_ticket("Avengers", "02/05/2025 10:00 AM", 100) # Booked at Grand Cinema
order_ticket("Avengers", "02/05/2025 10:00 AM", 50)  # ❌ Fails: No seats

start_show(show_id_2)
order_ticket("Avengers", "02/05/2025 10:00 AM", 10)  # ❌ Fails: Show started
end_show(show_id_2)
```

---

### **Test Case 5: Revenue Calculation with Refunds**

```
show_id_1 = registerShow("Grand Cinema", "Avengers", "02/05/2025 10:00 AM", 15.0, 100)
show_id_2 = registerShow("PVR", "Avengers", "02/05/2025 10:00 AM", 10.0, 100)

booking_id_1 = order_ticket("Avengers", "02/05/2025 10:00 AM", 60)  # PVR
booking_id_2 = order_ticket("Avengers", "02/05/2025 10:00 AM", 100) # Grand Cinema

cancel_booking(booking_id_1)  # ✅ Refund 50%

start_show(show_id_2)
cancel_booking(booking_id_2)  # ❌ No refund
end_show(show_id_2)

start_show(show_id_1)
end_show(show_id_1)

print_system_stats()
```

**Output:**

```
PVR, Revenue: 300
Grand Cinema, Revenue: 1500
```

---

## ⚡ Future Enhancements

* ✅ Support for **multi-threaded concurrent bookings** => done
* ✅ Detailed reporting per cinema => done 
* ✅ User authentication & payment integration 

---

## 👨‍💻 Author

**Adarsh**
💼 Backend Engineer | Scalable Systems Enthusiast

---

## 📝 License

This project is licensed under the **MIT License**.
