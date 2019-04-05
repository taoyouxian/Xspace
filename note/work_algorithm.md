- 单链表逆序

  ```
  ListNode* reverseList(ListNode* head) {
  	ListNode * pre = NULL;
  	ListNode * cur = head;
  	while (head)
  	{
  		head = head->next;
  		cur->next = pre;
  		pre = cur;
  		cur = head;
  	}
  	return pre;
  }
  ```

- [倒置链表](https://leetcode.com/problems/reverse-linked-list-ii/submissions/)（头插法）

  ```
  ListNode *reverseBetween(ListNode *head, int m, int n) {
  	ListNode *dummy = new ListNode(-1), *pre = dummy;
  	dummy->next = head;
  	for (int i = 0; i < m - 1; i++) pre = pre->next;
  	ListNode *cur = pre->next;
  	for (int i = m; i < n; i++) {
  		ListNode *t = cur->next;
  		cur->next = t->next;
  		t->next = pre->next;
  		pre->next = t;
  	}
  	return dummy->next;
  }
  ```

  