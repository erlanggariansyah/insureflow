import React, { useEffect, useState } from "react";
import DataTable from "components/table/DataTable";
import MasterDataLayout from "components/layout/MasterDataLayout";
import { productService } from "services/productService";
import { MdAdd, MdTimer, MdSettings } from "react-icons/md";

const columns = [
  { header: "ID", accessorKey: "id" },
  { header: "Code", accessorKey: "code" },
  { header: "Name", accessorKey: "name" },
  {
    header: "Factor",
    accessorKey: "factor",
    cell: info => <span className="font-mono font-bold text-blue-600">{info.getValue()}</span>
  },
  { header: "Active", accessorKey: "isActive", cell: info => info.getValue() ? <span className="text-green-500 font-bold">Yes</span> : <span className="text-red-500 font-bold">No</span> },
];

export default function PaymentFrequencyList() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    productService.getAllPaymentFrequencies()
      .then(res => setData(res.data || []))
      .catch(err => console.error(err))
      .finally(() => setLoading(false));
  }, []);

  const workspaceActions = [
    {
      label: "Add Frequency",
      desc: "Create new interval",
      icon: <MdAdd className="h-6 w-6" />,
      color: "text-emerald-500 bg-emerald-50 dark:bg-emerald-900/30",
      isPrimary: true,
      onClick: () => alert("Opening Add Frequency...")
    },
    {
      label: "Global Ratios",
      desc: "Manage factor logic",
      icon: <MdTimer className="h-5 w-5" />,
      color: "text-orange-500 bg-orange-50 dark:bg-orange-900/30",
      borderColor: "border-l-orange-500",
      onClick: () => alert("Manage Factors...")
    }
  ];

  return (
    <MasterDataLayout title="Workspace" actions={workspaceActions}>
      {loading ? (
        <div className="flex h-64 items-center justify-center">
          <span className="text-gray-400 font-bold animate-pulse uppercase tracking-widest text-xs">Syncing Intervals...</span>
        </div>
      ) : (
        <DataTable columns={columns} data={data} filename="PaymentFrequencies_Export" />
      )}
    </MasterDataLayout>
  );
}
