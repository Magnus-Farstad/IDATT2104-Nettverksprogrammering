#include <iostream>
#include <functional>
#include <list>
#include <condition_variable>
#include <thread>
#include <vector>
#include <mutex>

using namespace std;

class Workers {
public:
    bool wait = true;
    bool run = true;
    mutex tasks_mutex;
    condition_variable cv;
    list<function<void()>> tasks;
    vector<thread> worker_threads;
    int nrOfThreads;

    Workers(int nrOfThreads) {
        this->nrOfThreads = nrOfThreads;
    }



    void post_tasks(function<void()> task) {
        unique_lock<mutex> lock(tasks_mutex);
        tasks.emplace_back(task);
        cv.notify_one();
    }

    void run_tasks_in_worker_threads() {
        for (int i = 0; i < this -> nrOfThreads; i++) {
            worker_threads.emplace_back([this] {
                while(run) {
                    function<void()> task;
                    {
                        unique_lock<mutex> lock(tasks_mutex);
                        while(wait) {
                            cv.wait(lock);
                        }
                        if (!tasks.empty()) {
                            task = *tasks.begin();
                            tasks.pop_front();
                        }
                        //cv.notify_one();
                    }

                    unique_lock<mutex> lock(tasks_mutex);
                    if (task)
                        task();
                    else {
                        run = false;
                    }
                }
            });
            {
                unique_lock<mutex> lock(tasks_mutex);
                wait = false;
            }
            cv.notify_one();
        }
        for (auto &thread : worker_threads)
            thread.join();
    }

    void stop() {
        if(tasks.empty()) {
            this->run = false;
        }
    }

    void post_timeout(function<void()> task){
        tasks.emplace_back([task] {
            this_thread::sleep_for(5000ms);
            task();
        });
    }

};

int main() {
    Workers worker_threads(4);
    Workers event_loop(1);

    worker_threads.post_tasks([] {
        cout << "Oppgave A" << " kjørt av tråd "
             << this_thread::get_id()
             << endl;
    });

    worker_threads.post_tasks([] {
        cout << "Oppgave B" << " kjørt av tråd "
             << this_thread::get_id()
             << endl;
    });

    worker_threads.post_tasks([] {
        cout << "Oppgave C" << " kjørt av tråd "
             << this_thread::get_id()
             << endl;
    });

    event_loop.post_tasks([] {
        cout << "Oppgave D" << " kjørt av tråd "
             << this_thread::get_id()
             << endl;
    });

    event_loop.post_tasks([] {
        cout << "Oppgave E" << " kjørt av tråd "
             << this_thread::get_id()
             << endl;
    });


    event_loop.post_timeout([] {
        cout << "Oppgave F" << " kjørt av tråd "
             << this_thread::get_id() << ", med en delay på 5s"
             << endl;
    });

    worker_threads.run_tasks_in_worker_threads();
    event_loop.run_tasks_in_worker_threads();

    worker_threads.stop();
    event_loop.stop();
    //std::cout << "Hello, World!" << std::endl;
    return 0;
}
