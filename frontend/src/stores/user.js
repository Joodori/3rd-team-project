import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useUserInfo = defineStore('user', () => {

    const user = ref(true)

    const no_reserve = ref(false)

    const config = ref('')

    const login = ref(false)

    const user_info = ref({
        user_id : '',
        user_name : '',
        user_birth_date: '',
        user_age : '',
        user_address : '',
        user_mobile : ''
    })

    const ticket = ref({
        ticket_no : '',
        ticket_name : '',
        ticket_reserve_date : '',
        ticket_amount : '',
        ticket_money_status : ''
    })

    const ride = ref([])


    return{
        user, no_reserve, config, login, user_info, ticket, ride
    }

})