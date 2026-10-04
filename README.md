# Immigrant Application System (Enterprise Architecture Upgrade)

## Project Origin & Attribution
This application originated as a core academic group project for **CS321 at George Mason University**.
* **Original System Contributors:** Mostafa Mahtab, Shane Birkhead, Kavon Barr
* **Core Group Scope:** Initial Java Spring Boot REST services, data object schemas, and internal business logic queues.

## Individual Enterprise Upgrades (By Mostafa Mahtab)
This repository represents an independent, production-grade re-architecture of the original system to bridge the gap between academic code and cloud-portable DevOps standards.

**Individual Enterprise Upgrades include:**
* **Multi-Container Micro-Services:** Containerized the application into isolated, sandboxed environments using Docker and Docker Compose.
* **Database Volume Persistence:** Configured named Docker volumes to anchor MySQL data persistence directly to host machine disk tracks, replacing ephemeral runtimes.
* **Nginx Reverse Proxy Security:** Architected an Nginx gateway to handle connection masking and traffic routing over standard web ports, isolating backend ports from exposure.
* **Continuous Integration (CI/CD):** Designed a GitHub Actions pipeline script that automates Java setup, checks system code formats via Checkstyle quality gates, and guarantees stability by auto-executing the 11 unit tests on every code push.
